import { create } from 'zustand';
import { TagType } from './type';
import { SAMPLE_DATA } from '@/constants';

type SelectedTagState = {
  tagList: TagType[];
  selectedTagList: TagType[];
  fetchingTags: { isError: boolean; isPending: boolean; data: TagType[] };
};

type SelectedTagAction = {
  selectTag: (tag: TagType) => void;
  deselectTag: (tag: TagType) => void;
  fetchingTagList: () => Promise<void>;
};

const initialState: SelectedTagState = {
  tagList: [],
  selectedTagList: [],
  fetchingTags: { isError: false, isPending: false, data: [] },
};

export const useSelectTagStore = create<SelectedTagState & SelectedTagAction>(
  (set) => ({
    ...initialState,
    selectTag: (tag: TagType) =>
      set((state) => {
        const exist = state.selectedTagList.some((t) => t.id === tag.id);

        // 이미 선택된 태그인지 확인
        if (exist) {
          return state;
        }

        return { selectedTagList: [...state.selectedTagList, tag] };
      }),

    deselectTag: (tag: TagType) =>
      set((state) => ({
        selectedTagList: state.selectedTagList.filter((t) => t.id !== tag.id),
      })),

    fetchingTagList: async () => {
      try {
        set(() => ({
          fetchingTags: { ...initialState.fetchingTags, isPending: true },
        }));

        // TODO 서버 통신 코드 호출.
        setTimeout(() => {
          set(() => ({
            tagList: SAMPLE_DATA,
            fetchingTags: { ...initialState.fetchingTags, isPending: false },
          }));
        }, 3000);
      } catch (error) {
        if (error instanceof Error) {
          set((state) => ({
            fetchingTags: {
              ...state.fetchingTags,
              isError: true,
              isPending: false,
            },
          }));
        }
      }

      return;
    },
  })
);
