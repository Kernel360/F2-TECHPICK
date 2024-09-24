import React, { useEffect, useState } from 'react';
// import { REQUEST_OG_IMAGE } from '@/constants';
import { ResponseOgImageType } from '@/types';

interface TabInfo {
  title: string;
  url: string;
  ogImage?: string | null;
}

export const CurrentTabInfo: React.FC = () => {
  const [tabInfo, setTabInfo] = useState<TabInfo | null>(null);

  useEffect(() => {
    // 현재 탭 정보 가져오기
    chrome.tabs.query({ active: true, currentWindow: true }, (tabs) => {
      const tab = tabs[0];
      setTabInfo({ title: tab.title || 'No Title', url: tab.url || 'No URL' });
      return;
    });

    // 1. 요청 보내기
    console.log('send message');
    chrome.runtime.sendMessage('REQUEST_OG_IMAGE_TO_BACK');

    // 2. 응답 기다리기.
    console.log('waiting message');
    chrome.runtime.onMessage.addListener((message: ResponseOgImageType) => {
      if (message.type === 'OG_IMAGE_TO_COMPONENT') {
        console.log('get message on CurrentTabInfo', message);

        setTabInfo((prev) => {
          if (prev) {
            return {
              ...prev,
              ogImage: message.ogImageUrl,
            };
          }
          return prev;
        });
      }
    });
  }, []);

  return (
    <div>
      <h2>{tabInfo?.title}</h2>
      <p>URL: {tabInfo?.url}</p>
      {tabInfo?.ogImage ? (
        <img src={tabInfo.ogImage} alt="Open Graph" />
      ) : (
        <p>No OG Image</p>
      )}
    </div>
  );
};
