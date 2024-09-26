import { create } from 'zustand';
import { TagType } from './type';

type SelectedTagState = {
  tagList: TagType[];
};

type SelectedTagAction = {
  selectTag: (tag: TagType) => void;
  deselectTag: (tag: TagType) => void;
};

export const useSelectTagStore = create<SelectedTagState & SelectedTagAction>(
  (set) => ({
    tagList: [],
    selectTag: (tag: TagType) =>
      set((state) => {
        const exist = state.tagList.some((t) => t.id === tag.id);

        // 이미 선택된 태그인지 확인
        if (exist) {
          return state;
        }

        return { tagList: [...state.tagList, tag] };
      }),
    deselectTag: (tag: TagType) =>
      set((state) => ({
        tagList: state.tagList.filter((t) => t.id !== tag.id),
      })),
  })
);
