import { ApiTagData } from '@/shared/types/ApiTypes';
// import { components } from '@/schema';
// export type GetPickResponseType = components['schemas']['PickResponse'];

export type GetPickResponseType = {
  id: number;
  title: string;
  memo: string;
  folderId: number;
  userId: number;
  tagList: ApiTagData[];
  linkUrlResponse: LinkUrlResponse;
};

type LinkUrlResponse = { id: number; url: string; imageUrl: string | null };

export type UpdatePickRequestType = {
  id: number;
  title: string;
  memo: string;
  tagIdList: number[];
};
