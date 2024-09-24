import React, { useEffect, useState } from 'react';

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
      setTabInfo({
        title: tab.title || 'No Title',
        url: tab.url || 'No URL',
      });

      // 현재 탭의 content_script에 postMessage를 통해 Open Graph 이미지 요청
      chrome.scripting.executeScript({
        target: { tabId: tab.id || 0 },
        func: () => {
          console.log('post message');
          window.postMessage({ type: 'GET_OG_IMAGE' }, '*');
        },
      });

      // contentScript로부터 응답을 받는다.
      chrome.runtime.sendMessage({ greeting: 'hello' });
    });

    chrome.runtime.onMessage.addListener(
      (message: { type: string; ogImage: string | null }) => {
        if (message.type !== 'FROM_SCRIPT') {
          console.log('is not FROM_SCRIPT');

          return;
        }

        // 기존 상태와 병합하여 새로운 상태 설정
        setTabInfo((prev) => {
          if (!prev) return null; // prev가 null일 경우 처리
          return {
            ...prev,
            ogImage: message.ogImage, // 여기서 ogImage를 설정
          };
        });
      }
    );
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
