'use client';

import React from 'react';
import { useThemeStore } from '@/shared/stores/themeStore';
import { iconButton } from '@/widgets/DirectoryTreeSection/AddFolderPopoverButton.css';
import { Moon, Sun } from 'lucide-react';

export function ToggleThemeButton() {
  const { isDarkMode, toggleTheme } = useThemeStore();

  return (
    <div>
      <button className={iconButton} onClick={toggleTheme}>
        {isDarkMode ? <Moon strokeWidth={1} /> : <Sun strokeWidth={1} />}
      </button>
    </div>
  );
}
