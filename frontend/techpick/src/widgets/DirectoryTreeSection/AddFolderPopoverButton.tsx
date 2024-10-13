// PopoverDemo.tsx
import React from 'react';
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

const AddFolderPopoverButton = () => (
  <Popover.Root>
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
            <input className={input} defaultValue="New Folder" />
          </fieldset>
        </div>
        <Popover.Close className={popoverClose} aria-label="Close">
          <Cross2Icon />
        </Popover.Close>
      </Popover.Content>
    </Popover.Portal>
  </Popover.Root>
);

export default AddFolderPopoverButton;
