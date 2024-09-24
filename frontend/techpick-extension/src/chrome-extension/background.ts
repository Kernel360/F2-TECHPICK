import { RequestMessageType, ResponseOgImageType } from '@/types';

// TODO: 1. 현재는, 촤초 불러올 시에만 og:image를 가져오는 코드를 동작하게 할 수 있다. 공식 문서를 찾아보고 왜 안되는지 원인을 찾아보기.
// TODO:

// 1. script에서 og image를 받아온다.
let ResponseOgImage: ResponseOgImageType = {
  type: '',
  ogImageUrl: null,
};

chrome.runtime.onMessage.addListener((response: ResponseOgImageType) => {
  if (response.type === 'OG_IMAGE_FROM_SCRIPT') {
    console.log('get message from script', response);
    ResponseOgImage = response;
  }
});

// 2. popup에서 요청을 받는다.
chrome.runtime.onMessage.addListener((message: RequestMessageType) => {
  if (message === 'REQUEST_OG_IMAGE_TO_BACK') {
    console.log('get message on background', message);
    // 3. script에 요청을 보낸다.
    chrome.runtime.sendMessage<ResponseOgImageType>({
      type: 'OG_IMAGE_TO_COMPONENT',
      ogImageUrl: ResponseOgImage.ogImageUrl,
    });
  }
});
