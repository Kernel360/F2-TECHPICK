import { create } from 'zustand';
import { immer } from 'zustand/middleware/immer';
import { TagType, TagUpdateType } from '../type';
import { getTagList } from '../api';

type TagState = {
  tagList: TagType[];
  selectedTagList: TagType[];
  fetchingTagState: { isError: boolean; isPending: boolean; data: TagType[] };
  postTagState: { isError: boolean; isPending: boolean; isSuccess: boolean };
};

type TagAction = {
  selectTag: (tag: TagType) => void;
  deselectTag: (tag: TagType) => void;
  updateSelectedTagList: (tag: TagType) => void;
  fetchingTagList: () => Promise<void>;
  createTag: (
    newTagName: string,
    tagNumber: number
  ) => Promise<TagType | undefined>;
  deleteTag: (tagId: number) => Promise<void>;
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

    deselectTag: (tag: TagType) =>
      set((state) => {
        state.selectedTagList = state.selectedTagList.filter(
          (t) => t.tagId !== tag.tagId
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

        setTimeout(() => {
          set((state) => {
            state.tagList = [...remoteTagList];
            state.fetchingTagState.isPending = false;
          });
        }, 500);
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

    createTag: async (newTagName, tagNumber) => {
      try {
        set((state) => {
          state.postTagState.isPending = true;
        });

        // TODO: 나중에 비동기 더하기
        const newTag = await new Promise<TagType>((resolve) => {
          setTimeout(() => {
            const tag: TagType = {
              tagId: Date.now(),
              tagName: newTagName,
              colorNumber: tagNumber,
              tagOrder: Date.now(), // 임의의 값. 추후 수정해야한다.
              userId: Date.now(), // 임의의 값. 추후 수정해야한다.
            };

            set((state) => {
              state.tagList.push(tag);
              state.postTagState.isPending = false;
              state.postTagState.isSuccess = true;
            });

            resolve(tag);
          }, 500);
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
