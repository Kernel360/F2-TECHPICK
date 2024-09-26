import { SelectedTagListLayoutStyle } from './SelectedTagListLayout.css';

export function SelectedTagListLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return <div className={SelectedTagListLayoutStyle}>{children}</div>;
}
