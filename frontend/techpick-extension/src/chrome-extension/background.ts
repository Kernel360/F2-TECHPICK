import { REQUEST_FAVICON } from '@/constants';

const getFavicon = () => {
  const faviconLink =
    document.querySelector("link[rel='icon']") ||
    document.querySelector("link[rel='shortcut icon']");
  const faviconUrl =
    faviconLink instanceof HTMLLinkElement ? faviconLink.href : null;

  return faviconUrl; // 파비콘 URL 반환
};

// 메시지를 수신하여 파비콘을 가져오는 리스너
chrome.runtime.onMessage.addListener((message, _sender, sendResponse) => {
  if (message.type !== REQUEST_FAVICON || !message.tabId) {
    return;
  }

  const tabId = message.tabId;

  if (!tabId) {
    sendResponse({ favicon: null }); // tabId가 없을 경우 응답
  }

  // tabId가 정의된 경우에만 실행
  chrome.scripting.executeScript(
    {
      target: { tabId: tabId },
      func: getFavicon,
    },
    (results) => {
      // 파비콘을 찾지 못했을 경우
      if (!results || !results[0]) {
        sendResponse({ favicon: null });
      }

      const faviconUrl = results[0].result;
      sendResponse({ favicon: faviconUrl });
    }
  );

  return true;
  // 비동기 응답을 위해 true를 반환
  // chrome.scripting.executeScript가 비동기적이다.
});
