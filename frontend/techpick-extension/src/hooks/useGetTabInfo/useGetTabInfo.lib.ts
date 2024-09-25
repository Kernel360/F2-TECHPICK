const getFaviconFromDom = () => {
  const faviconLink =
    document.querySelector("link[rel='icon']") ||
    document.querySelector("link[rel='shortcut icon']") ||
    document.querySelector('link');

  return faviconLink instanceof HTMLLinkElement ? faviconLink.href : null;
};

export const fetchFavicon = async (tabId: number) => {
  const results = await chrome.scripting.executeScript({
    target: { tabId },
    func: getFaviconFromDom,
  });

  // 결과에서 파비콘 URL을 반환
  return results && results[0] ? results[0].result : null;
};
