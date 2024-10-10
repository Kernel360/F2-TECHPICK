export type TagType = {
  tagId: number;
  tagName: string;
  tagOrder: number;
  colorNumber: number;
  userId: number;
};

// type TagUpdateRequest = components['schemas']['TagUpdateRequest'][];

export type TagUpdateType = {
  tagId: number;
  tagName: string;
  tagOrder: number;
  colorNumber: number;
};

export type UpdateTagType = {
  tagId: 0;
  tagName: 'string';
  tagOrder: 0;
  colorNumber: 0;
};
