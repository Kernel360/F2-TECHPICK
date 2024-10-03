import { selectedTagLayoutStyle } from './SelectedTagLayout.css';

export function SelectedTagLayout({ children }: { children: React.ReactNode }) {
  return (
    <span className={selectedTagLayoutStyle} data-desc="dasd">
      {children}
    </span>
  );
}
