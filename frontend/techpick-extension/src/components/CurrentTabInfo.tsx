import { useEffect, useState } from 'react';

interface TabInfo {
  title: string;
  url: string;
  ogImage?: string | null;
  favicon?: string | null; // favicon 속성 추가
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

      console.log('tab.url', tab.url);

      if (
        tab.url &&
        !(tab.url.startsWith('http://') || tab.url.startsWith('https://'))
      ) {
        return;
      }

      // 현재 탭의 content_script에 postMessage를 통해 파비콘 이미지 요청
      console.log('send message REQUEST_FAVICON');
      chrome.runtime.sendMessage(
        { type: 'REQUEST_FAVICON', tabId: tab.id },
        (response) => {
          console.log('response', response);

          // 이전 상태를 기반으로 새로운 상태를 설정
          setTabInfo((prev) => {
            if (prev === null)
              return {
                title: 'No Title',
                url: 'No URL',
                favicon: response.favicon || null,
              }; // prev가 null인 경우 초기값 설정

            return {
              ...prev,
              favicon: response.favicon || null, // 응답에서 받은 파비콘 URL 설정
            };
          });
        }
      );
    });
  }, []);

  return (
    <div>
      <h2>{tabInfo?.title}</h2>
      <p>URL: {tabInfo?.url}</p>
      {tabInfo?.favicon ? (
        <img src={tabInfo.favicon} alt="Favicon" />
      ) : (
        <p>No Favicon</p>
      )}
      {tabInfo?.ogImage ? (
        <img src={tabInfo.ogImage} alt="Open Graph" />
      ) : (
        <p>No OG Image</p>
      )}
    </div>
  );
}
