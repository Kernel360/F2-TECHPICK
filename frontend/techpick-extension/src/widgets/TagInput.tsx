// TODO
// 1. select 부드럽게
// 2. 특정 tag 선택시에는 두 화면에서 다 보여야함.
// 3. 생성, 삭제 시에도 동일.

import { useRef, useState } from 'react';
import { Command } from 'cmdk';
import {
  tagInputStyle,
  tagDialogTrigger,
  tagDialogPortal,
  selectedTagItemStyle,
} from './TagInput.css';

const SAMPLE_DATA = ['a', 'b', 'c', 'd'];

export function TagInput() {
  const [open, setOpen] = useState(false);
  const tagInputContainerRef = useRef<HTMLDivElement | null>(null);
  const commandInputRef = useRef<HTMLInputElement | null>(null);

  const openDialog = () => {
    setOpen(true);
  };

  // useEffect(() => {
  //   const handleKeyDown = (event: KeyboardEvent) => {
  //     if (event.key === 'ArrowDown' || event.key === 'ArrowUp') {
  //       // 기본 동작을 커스텀으로 처리할 수 있음
  //       event.preventDefault();
  //       const items = document.querySelectorAll('[role="option"]');
  //       if (items.length === 0) return;

  //       let selectedIdx = Array.from(items).findIndex((item) =>
  //         item.classList.contains('cmdk-item-selected')
  //       );

  //       if (event.key === 'ArrowDown') {
  //         selectedIdx = (selectedIdx + 1) % items.length;
  //       } else if (event.key === 'ArrowUp') {
  //         selectedIdx = (selectedIdx - 1 + items.length) % items.length;
  //       }

  //       // 선택 상태 적용
  //       items[selectedIdx]?.scrollIntoView({ block: 'nearest' });
  //       (items[selectedIdx] as HTMLElement)?.focus();
  //     }
  //   };

  //   if (open) {
  //     document.addEventListener('keydown', handleKeyDown);
  //   } else {
  //     document.removeEventListener('keydown', handleKeyDown);
  //   }

  //   return () => {
  //     document.removeEventListener('keydown', handleKeyDown);
  //   };
  // }, [open]);

  return (
    <div
      data-description="모달이 여기서 뜨게."
      id="tagInputContainer"
      ref={tagInputContainerRef}
      className={tagInputStyle}
    >
      <div
        className={tagDialogTrigger}
        data-description="여기를 선택하면 다이얼로그가 켜짐"
        onClick={openDialog}
      >
        이쁜 칸
      </div>
      <Command.Dialog
        open={open}
        onOpenChange={setOpen}
        label="Global Command Menu"
        container={tagInputContainerRef.current ?? undefined}
        className={tagDialogPortal}
      >
        <Command.Input ref={commandInputRef} autoFocus />
        <Command.List>
          <Command.Empty>No results found.</Command.Empty>

          {SAMPLE_DATA.map((data) => (
            <Command.Item className={selectedTagItemStyle}>{data}</Command.Item>
          ))}
        </Command.List>
      </Command.Dialog>
    </div>
  );
}
