import { popoverOverlayStyle } from './TagInfoEditPopoverButton.css';

export function PopoverOverlay({
  onClick = () => {},
}: {
  onClick?: () => void;
}) {
  return <div className={popoverOverlayStyle} onClick={onClick} />;
}
