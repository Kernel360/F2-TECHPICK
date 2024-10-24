'use client';
import { lightTheme, darkTheme, commonTheme } from 'techpick-shared';
import { useThemeStore } from '@/stores/themeStore';
export const ThemeProvider = ({ children }: { children: React.ReactNode }) => {
  const { isDarkMode } = useThemeStore();

  const currentTheme = isDarkMode ? darkTheme : lightTheme;
  return <div className={`${commonTheme} ${currentTheme}`}>{children}</div>;
};
