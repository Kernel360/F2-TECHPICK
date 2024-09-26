import { selectedTagStyle } from './SelectedTag.css';

export function SelectedTag({ children }: { children: React.ReactNode }) {
  return <span className={selectedTagStyle}>{children}</span>;
}
