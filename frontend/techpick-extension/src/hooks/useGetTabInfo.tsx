import { useEffect, useState } from 'react';
import { REQUEST_FAVICON } from '@/constants';

interface TabInfo {
  title: string | undefined;
  url: string | undefined;
  favicon?: string | undefined | null;
}

export function useGetTabInfo() {
  const [tabInfo, setTabInfo] = useState<TabInfo>({
    title: undefined,
    url: undefined,
    favicon: undefined,
  });

  useEffect(() => {
    // 현재 탭 정보 가져오기
    chrome.tabs.query({ active: true, currentWindow: true }, (tabs) => {
      const tab = tabs[0];
      setTabInfo({
        title: tab.title,
        url: tab.url,
      });

      // http가 아닌 다른 url(ex: chrome)은 권한이 없어서 확장 프로그램에서 에러가 날 수 있습니다.
      if (tab.url && !tab.url.startsWith('http')) {
        return;
      }

      chrome.runtime.sendMessage(
        { type: REQUEST_FAVICON, tabId: tab.id },
        (response: { favicon: string | null | undefined }) => {
          setTabInfo((prev) => {
            return {
              ...prev,
              favicon: response.favicon,
            };
          });
        }
      );
    });
  }, []);

  return tabInfo;
}
