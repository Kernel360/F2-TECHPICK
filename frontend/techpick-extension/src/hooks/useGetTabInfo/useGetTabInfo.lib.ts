// executeScript는 전달된 함수의 실행 컨텍스트가 전역 스코프가 되기 때문에, 외부에서 정의한 함수에 접근할 수 없습니다.
const getPageInfoFromDom = () => {
  const getFaviconFromDom = () => {
    const faviconLink =
      document.querySelector("link[rel='icon']") ||
      document.querySelector("link[rel='shortcut icon']") ||
      document.querySelector('link');

    return faviconLink instanceof HTMLLinkElement ? faviconLink.href : null;
  };

  const getOgDescriptionFromDom = () => {
    const ogDescriptionMeta = document.querySelector(
      "meta[property='og:description']"
    );

    return ogDescriptionMeta instanceof HTMLMetaElement
      ? ogDescriptionMeta.content
      : null;
  };

  const faviconUrl = getFaviconFromDom();
  const ogDescription = getOgDescriptionFromDom();

  return {
    faviconUrl,
    ogDescription,
  };
};

export const fetchFaviconAndDescription = async (tabId: number) => {
  const results = await chrome.scripting.executeScript({
    target: { tabId },
    func: getPageInfoFromDom,
  });

  return results[0].result;
};
