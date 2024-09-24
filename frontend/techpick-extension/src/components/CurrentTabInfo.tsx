import { useEffect, useState } from 'react';

interface TabInfo {
  title: string;
  url: string;
  ogImage?: string | null;
}

export function CurrentTabInfo() {
  const [tabInfo, setTabInfo] = useState<TabInfo | null>(null);

  useEffect(() => {
    // 현재 탭 정보 가져오기
    chrome.tabs.query({ active: true, currentWindow: true }, (tabs) => {
      const tab = tabs[0];
      setTabInfo({
        title: tab.title || 'No Title',
        url: tab.url || 'No URL',
      });

      // 현재 탭의 content_script에 postMessage를 통해 파비콘 이미지 요청
      chrome.scripting.executeScript({
        target: { tabId: tab.id || 0 },
        func: () => {
          window.postMessage({ type: 'REQUEST_FAVICON' }, '*');
        },
      });
    });

    chrome.runtime.onMessage.addListener(
      (message: { type: string; ogImage: string | null }) => {
        if (message.type !== 'RESPONSE_FAVICON') {
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
}
