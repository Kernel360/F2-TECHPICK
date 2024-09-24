// background.js
const getFavicon = () => {
  const faviconLink =
    document.querySelector("link[rel='icon']") ||
    document.querySelector("link[rel='shortcut icon']");
  const faviconUrl =
    faviconLink instanceof HTMLLinkElement ? faviconLink.href : null;

  console.log('faviconUrl', faviconUrl);

  return faviconUrl; // 파비콘 URL 반환
};

// 메시지를 수신하여 파비콘을 가져오는 리스너
chrome.runtime.onMessage.addListener((message, sender, sendResponse) => {
  if (message.type === 'REQUEST_FAVICON') {
    console.log('sender', sender);

    const tabId = message.tabId; // optional chaining 사용

    console.log('background work', tabId);

    if (tabId) {
      // tabId가 정의된 경우에만 실행
      chrome.scripting.executeScript(
        {
          target: { tabId: tabId },
          func: getFavicon,
        },
        (results) => {
          console.log('background results', results);

          if (results && results[0]) {
            const faviconUrl = results[0].result;
            sendResponse({ favicon: faviconUrl }); // 응답으로 파비콘 URL 반환
          } else {
            sendResponse({ favicon: null }); // 파비콘을 찾지 못했을 경우
          }
        }
      );
    } else {
      sendResponse({ favicon: null }); // tabId가 없을 경우 응답
    }
    return true; // 비동기 응답을 위해 true를 반환
  }
});
