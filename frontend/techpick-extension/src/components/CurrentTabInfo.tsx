import React, { useEffect, useState } from 'react';
import { container } from './CurrentTabInfo.css';

export const CurrentTabInfo: React.FC = () => {
  const [tabInfo, setTabInfo] = useState<{ title: string; url: string } | null>(
    null
  );

  useEffect(() => {
    const getCurrentTab = async () => {
      chrome.tabs.query({ active: true, currentWindow: true }, (tabs) => {
        if (tabs[0]) {
          setTabInfo({
            title: tabs[0].title || 'No Title',
            url: tabs[0].url || 'No URL',
          });
        }
      });
    };

    getCurrentTab();
  }, []);

  if (!tabInfo) return <div>Loading...</div>;

  return (
    <div className={container}>
      <h1>Current Tab Info</h1>
      <p>Title: {tabInfo.title}</p>
      <p>URL: {tabInfo.url}</p>
    </div>
  );
};
