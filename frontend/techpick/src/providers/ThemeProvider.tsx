'use client';
import { useThemeStore } from '@/lib/stores/themeStore';
import { darkTheme } from '@/shared/themes/themeDark.css';
import { lightTheme } from '@/shared/themes/themeLight.css';
import { commonThemeCss } from '@/shared/themes/commonTheme.css';

export const ThemeProvider = ({ children }: { children: React.ReactNode }) => {
  const { isDarkMode } = useThemeStore();

  const currentTheme = isDarkMode ? darkTheme : lightTheme;
  return <div className={`${commonThemeCss} ${currentTheme}`}>{children}</div>;
};
