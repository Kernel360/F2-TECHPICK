import { create } from 'zustand';
import { immer } from 'zustand/middleware/immer';
import { TagType, TagUpdateType, CreateTagRequestType } from '../type';
import { getTagList, createTag } from '../api';

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

        // TODO: 나중에 비동기 붙이기.
        setTimeout(() => {
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
          });
        }, 500);
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

        // TODO: 비동기 처리 예시. 나중에 서버 통신 등으로 교체.
        await new Promise<void>((resolve) => {
          setTimeout(() => {
            // Promise를 resolve하여 비동기 처리가 끝났음을 알림
            resolve();
          }, 500);
        });

        set((state) => {
          const index = state.tagList.findIndex(
            (tag) => tag.tagId === updatedTag.tagId
          );

          if (index === -1) {
            return;
          }

          // 태그 업데이트
          state.tagList[index] = {
            ...updatedTag,
            userId: state.tagList[index].userId,
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
