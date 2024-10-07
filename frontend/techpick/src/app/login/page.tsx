'use client';

import Link from 'next/link';

export default function LoginPage() {
  return (
    <div>
      <div>
        <Link href={`${process.env.NEXT_PUBLIC_API}/login/google`}>
          구글로 로그인 하기
        </Link>
      </div>
      <div>
        <Link href={`${process.env.NEXT_PUBLIC_API}/login/kakao`}>
          카카오로 로그인 하기
        </Link>
      </div>
    </div>
  );
}
