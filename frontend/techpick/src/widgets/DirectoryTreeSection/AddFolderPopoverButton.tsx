import React, { useState } from 'react';
import * as Popover from '@radix-ui/react-popover';
import { MixerHorizontalIcon, Cross2Icon } from '@radix-ui/react-icons';
import {
  popoverContent,
  popoverClose,
  iconButton,
  fieldset,
  label,
  input,
  text,
} from './AddFolderPopoverButton.css';

interface AddFolderPopoverButtonProps {
  onEditEnded: (name: string) => void;
}

const AddFolderPopoverButton = ({
  onEditEnded,
}: AddFolderPopoverButtonProps) => {
  const [isOpen, setIsOpen] = useState(false);
  return (
    <Popover.Root open={isOpen} onOpenChange={setIsOpen}>
      <Popover.Trigger asChild>
        <button className={iconButton} aria-label="Update dimensions">
          <MixerHorizontalIcon />
        </button>
      </Popover.Trigger>
      <Popover.Portal>
        <Popover.Content className={popoverContent} sideOffset={5}>
          <div>
            <p className={text}>Add Folder</p>
            <fieldset className={fieldset}>
              <label className={label} htmlFor="width">
                Name
              </label>
              <input
                className={input}
                defaultValue="New Folder"
                onKeyDown={(e) => {
                  if (e.key === 'Enter') {
                    onEditEnded(e.currentTarget.value);
                    setIsOpen(false);
                  }
                }}
              />
            </fieldset>
          </div>
          <Popover.Close className={popoverClose} aria-label="Close">
            <Cross2Icon />
          </Popover.Close>
        </Popover.Content>
      </Popover.Portal>
    </Popover.Root>
  );
};

export default AddFolderPopoverButton;
