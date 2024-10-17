'use client';

import { PropsWithChildren } from 'react';
import Image from 'next/image';
import {
  pickCardLayout,
  cardImageSectionStyle,
  cardTitleSectionStyle,
  cardDescriptionSectionStyle,
  cardImageStyle,
} from './pickCard.css';

export function PickCard({
  children,
  pickId,
}: PropsWithChildren<PickCardProps>) {
  // 아래 값들은 다음 PR에서 id값으로 api통신을 이용해 값 받아올 예정.
  const imageUrl =
    'https://www.fitpetmall.com/wp-content/uploads/2023/10/shutterstock_602702633-1024x351-1.png';
  const titleValue = 'title';
  const memoValue = 'memo';

  console.log('pickId:', pickId);

  return (
    <div className={pickCardLayout}>
      <div className={cardImageSectionStyle}>
        <Image
          src={imageUrl}
          width={280}
          height={64}
          className={cardImageStyle}
          alt=""
        />
      </div>

      <div className={cardTitleSectionStyle}>
        <p>{titleValue}</p>
      </div>
      <div className={cardDescriptionSectionStyle}>
        <p>{memoValue}</p>
      </div>
      <div>{children}</div>
    </div>
  );
}
interface PickCardProps {
  pickId: number;
}
