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

export type CreateTagRequestType = components['schemas']['TagCreateRequest'];
export type CreateTagResponseType = TagType;
// export type CreateTagResponse = components['schemas']['TagResponse'];

//export type UpdateTagRequestType = components['schemas']['TagUpdateRequest'][];
export type UpdateTagRequestType = {
  tagId: number;
  tagName: string;
  tagOrder: number;
  colorNumber: number;
}[];
//export type UpdateTagResponseType = components['schemas']['TagResponse'][];
export type UpdateTagResponseType = {
  tagId: number;
  tagName: string;
  tagOrder: number;
  colorNumber: number;
  userId: number;
}[];
