import { X } from 'lucide-react';

export function DeselectTagButton() {
  return (
    <button
      type="button"
      onClick={() => {
        console.log('deselect');
      }}
    >
      <X size={8} />
    </button>
  );
}
