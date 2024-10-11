import { create } from 'zustand';
import { immer } from 'zustand/middleware/immer';
import { TagType, TagUpdateType, CreateTagRequestType } from '../type';
import { getTagList, createTag, deleteTag, updateTag } from '../api';

type TagState = {
  tagList: TagType[];
  selectedTagList: TagType[];
  fetchingTagState: { isError: boolean; isPending: boolean; data: TagType[] };
  postTagState: { isError: boolean; isPending: boolean; isSuccess: boolean };
};

type TagAction = {
  selectTag: (tag: TagType) => void;
  deselectTag: (tagId: TagType['tagId']) => void;
  updateSelectedTagList: (tag: TagType) => void;
  fetchingTagList: () => Promise<void>;
  createTag: (tagData: CreateTagRequestType) => Promise<TagType | undefined>;
  deleteTag: (tagId: TagType['tagId']) => Promise<void>;
  updateTag: (updatedTag: TagUpdateType) => Promise<void>;
};

const initialState: TagState = {
  tagList: [],
  selectedTagList: [],
  fetchingTagState: { isError: false, isPending: false, data: [] },
  postTagState: { isError: false, isPending: false, isSuccess: false },
};

export const useTagStore = create<TagState & TagAction>()(
  immer((set) => ({
    ...initialState,
    selectTag: (tag: TagType) =>
      set((state) => {
        const exist = state.selectedTagList.some((t) => t.tagId === tag.tagId);

        // 이미 선택된 태그인지 확인
        if (exist) {
          return;
        }

        state.selectedTagList.push(tag);
      }),

    deselectTag: (tagId) =>
      set((state) => {
        state.selectedTagList = state.selectedTagList.filter(
          (t) => t.tagId !== tagId
        );
      }),

    updateSelectedTagList: (updatedTag) => {
      set((state) => {
        const index = state.selectedTagList.findIndex(
          (tag) => tag.tagId === updatedTag.tagId
        );

        if (index === -1) {
          return;
        }

        state.selectedTagList[index] = {
          ...updatedTag,
        };
      });
    },

    fetchingTagList: async () => {
      try {
        set((state) => {
          state.fetchingTagState.isPending = true;
        });

        const remoteTagList = await getTagList();

        set((state) => {
          state.tagList = [...remoteTagList];
          state.fetchingTagState.isPending = false;
        });
      } catch (error) {
        if (error instanceof Error) {
          set((state) => {
            state.fetchingTagState.isPending = false;
            state.fetchingTagState.isError = true;
          });
        }
      }

      return;
    },

    createTag: async (tagData) => {
      try {
        set((state) => {
          state.postTagState.isPending = true;
        });

        const newTag = await createTag(tagData);

        set((state) => {
          state.tagList.push(newTag!);
          state.postTagState.isPending = false;
          state.postTagState.isSuccess = true;
        });

        return newTag;
      } catch (error) {
        if (error instanceof Error) {
          set((state) => {
            state.postTagState = {
              isError: true,
              isSuccess: false,
              isPending: false,
            };
          });
        }

        return;
      }
    },

    deleteTag: async (tagId: number) => {
      try {
        set((state) => {
          state.postTagState = { ...state.postTagState, isPending: true };
        });

        await deleteTag(tagId);

        set((state) => {
          const index = state.tagList.findIndex((tag) => tag.tagId === tagId);

          if (index === -1) {
            return;
          }

          state.tagList.splice(index, 1); // 태그 삭제
          state.postTagState = {
            ...state.postTagState,
            isPending: false,
            isSuccess: true,
          };

          const selectedIndex = state.selectedTagList.findIndex(
            (tag) => tag.tagId === tagId
          );

          if (selectedIndex === -1) {
            return;
          }

          state.selectedTagList.splice(selectedIndex, 1);
        });
      } catch (error) {
        if (error instanceof Error) {
          set((state) => {
            state.postTagState = {
              isError: true,
              isSuccess: false,
              isPending: false,
            };
          });
        }
      }
    },

    updateTag: async (updatedTag) => {
      try {
        // TODO: optimistic update 추가
        set((state) => {
          state.postTagState = { ...state.postTagState, isPending: true };
        });

        const dataList = await updateTag(updatedTag);
        const data = dataList[0];

        set((state) => {
          const index = state.tagList.findIndex(
            (tag) => tag.tagId === data.tagId
          );

          if (index === -1) {
            return;
          }

          state.tagList[index] = {
            ...data,
            userId: state.tagList[index].userId,
          };

          const selectedTagListIndex = state.selectedTagList.findIndex(
            (tag) => tag.tagId === data.tagId
          );

          if (selectedTagListIndex === -1) {
            return;
          }

          state.selectedTagList[selectedTagListIndex] = {
            ...data,
            userId: state.selectedTagList[selectedTagListIndex].userId,
          };

          state.postTagState = {
            ...state.postTagState,
            isPending: false,
            isSuccess: true,
          };
        });
      } catch (error) {
        if (error instanceof Error) {
          set((state) => {
            state.postTagState = {
              isError: true,
              isSuccess: false,
              isPending: false,
            };
          });
        }
      }
    },
  }))
);
