'use client';
import { useThemeStore } from '@/lib/stores/themeStore';
import { darkThemeClass } from '@/lib/themes/themeDark.css';
import { lightThemeClass } from '@/lib/themes/themeLight.css';

export const ThemeProvider = ({ children }: { children: React.ReactNode }) => {
  const { isDarkMode } = useThemeStore();

  const themeClass = isDarkMode ? darkThemeClass : lightThemeClass;
  return <div className={themeClass}>{children}</div>;
};
