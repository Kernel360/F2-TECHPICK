'use client';

import React from 'react';
import { useThemeStore } from '@/lib/stores/themeStore';

export const ToggleTheme = () => {
  const { isDarkMode, toggleTheme } = useThemeStore();

  return (
    <div>
      <button onClick={toggleTheme}>
        {isDarkMode ? 'DarkTheme' : 'LightTheme'}
      </button>
    </div>
  );
};

export default ToggleTheme;
