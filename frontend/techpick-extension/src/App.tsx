import { useEffect, useState } from 'react';
import reactLogo from './assets/react.svg';
import viteLogo from '/vite.svg';
import './App.css';
import { CurrentTabInfo } from './CurrentTabInfo';

function App() {
  const [count, setCount] = useState(0);
  const [bookmarks, setBookmarks] = useState<{ url: string; title: string }[]>(
    []
  );

  useEffect(() => {
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    const handleMessage = (message: any) => {
      if (message.type === 'BOOKMARK_CREATED') {
        setBookmarks((prev) => [
          ...prev,
          { url: message.url, title: message.title },
        ]);
      }
    };

    chrome.runtime.onMessage.addListener(handleMessage);

    return () => {
      chrome.runtime.onMessage.removeListener(handleMessage);
    };
  }, []);

  return (
    <>
      <CurrentTabInfo />
      <div>
        <h1>Bookmarks</h1>
        <ul>
          {bookmarks.map((bookmark, index) => (
            <li key={index}>
              <a href={bookmark.url}>{bookmark.title}</a>
            </li>
          ))}
        </ul>
      </div>
      <div>
        <a href="https://vitejs.dev" target="_blank">
          <img src={viteLogo} className="logo" alt="Vite logo" />
        </a>
        <a href="https://react.dev" target="_blank">
          <img src={reactLogo} className="logo react" alt="React logo" />
        </a>
      </div>
      <h1>Vite + React</h1>
      <div className="card">
        <button onClick={() => setCount((count) => count + 1)}>
          count is {count}
        </button>
        <p>
          Edit <code>src/App.tsx</code> and save to test HMR
        </p>
      </div>
      <p className="read-the-docs">
        Click on the Vite and React logos to learn more
      </p>
    </>
  );
}

export default App;
