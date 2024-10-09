import { selectedTagLayoutStyle } from './SelectedTagLayout.css';

export function SelectedTagLayout({ children }: { children: React.ReactNode }) {
  return <span className={selectedTagLayoutStyle}>{children}</span>;
}
