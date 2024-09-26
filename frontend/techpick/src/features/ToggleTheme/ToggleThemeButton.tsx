'use client';

import React from 'react';
import { useThemeStore } from '@/lib/stores/themeStore';

export function ToggleThemeButton() {
  const { isDarkMode, toggleTheme } = useThemeStore();

  return (
    <div>
      <button onClick={toggleTheme}>{isDarkMode ? 'Dark' : 'Light'}</button>
    </div>
  );
}
