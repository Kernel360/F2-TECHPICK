import { components } from '@/schema';

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

export type CreateTagRequestType = components['schemas']['TagCreateRequest'];
export type CreateTagResponseType = TagType;
// export type CreateTagResponse = components['schemas']['TagResponse'];
