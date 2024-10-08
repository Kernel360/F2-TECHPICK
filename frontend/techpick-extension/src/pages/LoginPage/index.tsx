import { BookMarked } from 'lucide-react';
import { Text, Button } from '@/shared';
import { loginPageLayout } from './LoginPage.css';

export function LoginPage() {
  return (
    <div className={loginPageLayout}>
      <BookMarked size={96} strokeWidth={1} />
      <Text size="2xl" asChild>
        <h1>태그와 함께 북마크하세요!</h1>
      </Text>

      <a href={`${import.meta.env.VITE_PUBLIC_DOMAIN}/login`} target="_blank">
        <Button>로그인</Button>
      </a>
    </div>
  );
}
