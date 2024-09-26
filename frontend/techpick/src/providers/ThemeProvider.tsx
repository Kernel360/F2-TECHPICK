'use client';
import { useThemeStore } from '@/lib/stores/themeStore';
import { darkTheme } from '@/shared/themes/darkTheme';
import { lightTheme } from '@/shared/themes/lightTheme';
import { commonTheme } from '@/shared/themes/commonTheme';

export const ThemeProvider = ({ children }: { children: React.ReactNode }) => {
  const { isDarkMode } = useThemeStore();

  const currentTheme = isDarkMode ? darkTheme : lightTheme;
  return <div className={`${commonTheme} ${currentTheme}`}>{children}</div>;
};
