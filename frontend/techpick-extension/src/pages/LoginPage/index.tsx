import { BookMarked } from 'lucide-react';
import { colorThemeContract } from 'techpick-shared';
import { Text, Button, PUBLIC_DOMAIN } from '@/shared';
import { loginPageLayout } from './LoginPage.css';

export function LoginPage() {
  return (
    <div className={loginPageLayout}>
      <BookMarked
        size={96}
        strokeWidth={1}
        color={colorThemeContract.color.font}
      />
      <Text size="2xl" asChild>
        <h1>태그와 함께 북마크하세요!</h1>
      </Text>

      <a href={`${PUBLIC_DOMAIN}/login`} target="_blank">
        <Button>로그인</Button>
      </a>
    </div>
  );
}
