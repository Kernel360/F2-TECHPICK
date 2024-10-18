'use client';

import { PropsWithChildren } from 'react';
import Image from 'next/image';
import { useGetPickQuery } from '../api';
import {
  pickCardLayout,
  cardImageSectionStyle,
  cardTitleSectionStyle,
  cardDescriptionSectionStyle,
  cardImageStyle,
} from './pickCard.css';
import { useDragHook } from '@/features/nodeManagement/hooks/useDragHook';
import { NodeApi } from 'react-arborist';

export function PickCard({ children, node }: PropsWithChildren<PickCardProps>) {
  // 아래 값들은 다음 PR에서 id값으로 api통신을 이용해 값 받아올 예정.
  const baseImageUrl =
    'https://www.fitpetmall.com/wp-content/uploads/2023/10/shutterstock_602702633-1024x351-1.png';

  const {
    data: pickData,
    isLoading,
    isError,
  } = useGetPickQuery(node.data.pickId);
  const ref = useDragHook(node);

  if (isLoading) {
    return <p>loading</p>;
  }

  if (isError || !pickData) {
    return <p>oops! something is wrong</p>;
  }

  const { memo, title } = pickData;

  return (
    <div
      className={pickCardLayout}
      ref={ref as unknown as React.LegacyRef<HTMLDivElement>}
    >
      <div className={cardImageSectionStyle}>
        <Image
          src={baseImageUrl}
          width={280}
          height={64}
          className={cardImageStyle}
          alt=""
        />
      </div>

      <div className={cardTitleSectionStyle}>
        <p>{title}</p>
      </div>
      <div className={cardDescriptionSectionStyle}>
        <p>{memo}</p>
      </div>
      <div>{children}</div>
    </div>
  );
}
interface PickCardProps {
  pickId: number;
  node: NodeApi;
}
