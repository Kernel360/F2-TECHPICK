import {
  imageStyle,
  imagePlaceholderStyle,
  fadeInStyle,
} from './ThumbnailImage.css';

export function ThumbnailImage({ image }: ThumbnailImageProps) {
  return (
    <>
      {image ? (
        <img
          src={image}
          alt="Bookmark page image"
          className={`${imageStyle} ${fadeInStyle}`} // 로딩 중에는 fadeInStyle 적용
        />
      ) : (
        <div className={imagePlaceholderStyle} />
      )}
    </>
  );
}

// ThumbnailImageProps 인터페이스 정의
interface ThumbnailImageProps {
  image?: string | null;
}
