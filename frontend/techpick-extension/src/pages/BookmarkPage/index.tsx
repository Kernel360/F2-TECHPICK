import { Gap, DeferredComponent } from '@/shared';
import { useGetTabInfo } from '@/entities/pick';
import { useHasPick } from '@/features/pick';
import { BookmarkHeader } from './BookmarkHeader';
import { SkeltonPickForm } from './SkeltonPickForm';
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
  const { isLoading, hasLink, data: pickData } = useHasPick(url);

  if (isLoading) {
    return (
      <div className={bookmarkPageLayout}>
        <BookmarkHeader />
        <Gap verticalSize="gap24" />
        <DeferredComponent>
          <SkeltonPickForm />
        </DeferredComponent>
      </div>
    );
  }

  return (
    <div className={bookmarkPageLayout}>
      <BookmarkHeader />
      <Gap verticalSize="gap24" />
      {hasLink ? (
        <UpdatePickForm
          id={pickData.id}
          title={pickData.title}
          tagList={pickData.tagList}
          memo={pickData.memo}
          imageUrl={imageUrl}
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
