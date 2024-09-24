window.addEventListener('message', (event) => {
  // 다른 페이지에서 오는 메시지는 무시
  if (event.source !== window || event.data.type !== 'GET_OG_IMAGE') return;

  // Open Graph 이미지 추출
  const ogImageTag =
    document.querySelector("meta[property='og:image']") ||
    document.querySelector("meta[name='og:image']");
  const ogImageUrl =
    ogImageTag instanceof HTMLMetaElement ? ogImageTag.content : null;

  // Open Graph 이미지 추출 후 메시지를 페이지에 다시 보냄
  chrome.runtime.sendMessage({ type: 'FROM_SCRIPT', ogImage: ogImageUrl });
});
