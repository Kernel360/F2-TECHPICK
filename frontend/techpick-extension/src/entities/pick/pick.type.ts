import type { components } from '@/schema';

export type CreatePickRequestType = components['schemas']['PickCreateRequest'];
export type CreatePickResponseType = components['schemas']['PickResponse'];
// export type GetPickResponseType = components['schemas']['PickResponse'];
export type GetPickResponseType = {
  id: number;
  title: string;
  memo: string;
  folderId: number;
  userId: number;
  tagList: TagList[];
  linkUrlResponse: LinkUrlResponse;
};

type TagList = {
  tagId: number;
  tagName: string;
  tagOrder: number;
  colorNumber: number;
  userId: number;
};

type LinkUrlResponse = {
  id: number;
  url: string;
  imageUrl: string;
};

// export type UpdatePickRequest = components['schemas']['PickUpdateRequest'];
export type UpdatePickRequest = {
  id: number;
  title: string;
  memo: string;
  tagIdList: number[];
};
// export type UpdatePickRequestType = components['schemas']['PickResponse'];
