// window.addEventListener('message', (event) => {
//   // 다른 페이지에서 오는 메시지는 무시
//   if (event.source !== window || event.data.type !== 'REQUEST_FAVICON') return;

//   // 파비콘 추출
//   const faviconLink =
//     document.querySelector("link[rel='icon']") ||
//     document.querySelector("link[rel='shortcut icon']");
//   const faviconUrl =
//     faviconLink instanceof HTMLLinkElement ? faviconLink.href : null;

//   // Open Graph 이미지 추출 후 메시지를 페이지에 다시 보냄
//   chrome.runtime.sendMessage({ type: 'RESPONSE_FAVICON', ogImage: faviconUrl });
// });
