'use client';
import { useThemeStore } from '@/lib/stores/themeStore';
import { lightTheme, darkTheme, commonTheme } from '@/shared/themes';
export const ThemeProvider = ({ children }: { children: React.ReactNode }) => {
  const { isDarkMode } = useThemeStore();

  const currentTheme = isDarkMode ? darkTheme : lightTheme;
  return <div className={`${commonTheme} ${currentTheme}`}>{children}</div>;
};
