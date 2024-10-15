import { Gap } from '@/shared';
import { useGetTabInfo } from '@/entities/pick';
import { useHasLink } from '@/features/link';
import { BookmarkHeader } from './BookmarkHeader';
import { SkeltonPickForm } from './SkeletonPickForm';
import { CreatePickForm } from './CreatePickForm';
import { UpdatePickForm } from './UpdatePickForm';
import { bookmarkPageLayout } from './BookmarkPage.css';

export function BookmarkPage() {
  const {
    ogImage: imageUrl,
    title,
    url,
    ogDescription: description,
  } = useGetTabInfo();
  const { isLoading, hasLink, data } = useHasLink(url);

  if (isLoading) {
    return (
      <div className={bookmarkPageLayout}>
        <BookmarkHeader />
        <Gap verticalSize="gap24" />
        <SkeltonPickForm title={title} />
      </div>
    );
  }

  if (hasLink) {
    console.log(data);
  }

  return (
    <div className={bookmarkPageLayout}>
      <BookmarkHeader />
      <Gap verticalSize="gap24" />
      {hasLink ? (
        <UpdatePickForm
          title={data.title}
          url={url}
          imageUrl={imageUrl}
          description={data.description}
        />
      ) : (
        <CreatePickForm
          title={title}
          url={url}
          imageUrl={imageUrl}
          description={description}
        />
      )}
    </div>
  );
}
