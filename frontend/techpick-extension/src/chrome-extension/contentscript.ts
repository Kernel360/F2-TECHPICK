// import { REQUEST_OG_IMAGE } from '@/constants';
import { ResponseOgImageType } from '@/types';

console.log('adadadas');

const ogImageTag =
  document.querySelector("meta[property='og:image']") ||
  document.querySelector("meta[name='og:image']");

const ogImageUrl =
  ogImageTag instanceof HTMLMetaElement ? ogImageTag.content : null;

console.log('ogImageUrl', ogImageUrl);

// 1. back에 응답을 보내준다.
chrome.runtime.sendMessage<ResponseOgImageType>({
  type: 'OG_IMAGE_FROM_SCRIPT',
  ogImageUrl,
});
