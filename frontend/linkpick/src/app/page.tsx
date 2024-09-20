'use client';

import toast from 'react-hot-toast';

const notify = () => toast('Here is your toast.');

//const a = 1;

export default function Home() {
  return (
    <div>
      <h1>This is main page</h1>
      <button onClick={notify}>aa</button>
    </div>
  );
}
