import { imageStyle, imagePlaceholderStyle } from './ThumbnailImage.css';

export function ThumbnailImage({ image }: ThumbnailImageProps) {
  return image ? (
    <img src={image} alt="Open Graph Image" className={imageStyle} />
  ) : (
    <div className={imagePlaceholderStyle} />
  );
}

interface ThumbnailImageProps {
  image?: string | null;
}
