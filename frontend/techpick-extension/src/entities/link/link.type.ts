// 추후 스웨거가 수정되면, 주석된 타입을 사용할 예정입니다.
// export type HasLinkResponseType = components['schemas']['LinkResponse'];
export type GetLinkResponseType = {
  id: number;
  url: string;
  title: string;
  description: string;
  imageUrl: string;
};
