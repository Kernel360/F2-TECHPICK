import { useGetTabInfo } from '@/hooks';

export function CurrentTabInfo() {
  const tabInfo = useGetTabInfo();

  return (
    <div>
      <h2>{tabInfo?.title}</h2>
      <p>URL: {tabInfo?.url}</p>
      {tabInfo?.favicon ? (
        <img src={tabInfo?.favicon} alt="Favicon" />
      ) : (
        <p>No Favicon</p>
      )}
      {tabInfo.ogDescription ? (
        <p>{tabInfo.ogDescription}</p>
      ) : (
        <p>No Og description</p>
      )}
    </div>
  );
}
