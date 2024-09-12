'use client';

import React, { useState } from 'react';

export function Come() {
  const [count, setCount] = useState(0);

  React.useEffect(() => {
    console.log('Component mounted');
  }, []);

  return (
    <div>
      <h1>ㅁㅁㅁHelloㅁ, Next.js with ESLint!</h1>
      <p>Counter: {count}</p>
      <button onClick={() => setCount(count + 1)}>Increment</button>
    </div>
  );
}
