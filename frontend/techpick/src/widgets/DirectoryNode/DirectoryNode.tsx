import { DirectoryNodeProps } from '@/shared/types/NodeData';
import {
  dirIcFolder,
  dirNode,
  dirNodeWrapper,
  dirNodeWrapperFocused,
  nodeNameInput,
} from './DirectoryNode.css';
import Image from 'next/image';
import { ChevronRight, ChevronDown } from 'lucide-react';
import { useQueryClient } from '@tanstack/react-query';
import { useCreateFolder } from '@/features/nodeManagement/hooks/useCreateFolder';

export const DirectoryNode = ({
  node,
  style,
  dragHandle,
}: DirectoryNodeProps) => {
  const queryClient = useQueryClient();
  const { mutateAsync: createFolder } = useCreateFolder();

  const handleKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
    if (event.key === 'Enter') {
      if (node.data.folderId === -1) {
        // 폴더 생성 (서버)
        const newFolderData = createFolder(event.currentTarget.value);
        console.log('Server: Folder created:', newFolderData);
      } else node.submit(event.currentTarget.value);
    }
    if (event.key === 'Escape') {
      if (node.data.folderId === -1) {
        queryClient.invalidateQueries({
          queryKey: ['rootAndRecycleBinData'],
          exact: true,
        });
      } else node.reset();
    }
  };

  return (
    <div
      style={{ ...style }}
      className={node.isSelected ? dirNodeWrapperFocused : dirNodeWrapper}
      ref={dragHandle}
      onClick={() => {
        node.toggle();
        console.log('Clicked Node', node);
      }}
      onContextMenu={() => {
        node.select();
      }}
    >
      <div className={dirNode}>
        {node.isOpen ? (
          <ChevronDown size={13} />
        ) : node.isLeaf ? (
          <div style={{ marginLeft: '13px' }}></div>
        ) : (
          <ChevronRight size={13} />
        )}
        {node.data.type === 'folder' ? (
          <Image
            src={`image/ic_folder.svg`}
            alt={`${node.data.name}'s image`}
            className={dirIcFolder}
            width={8}
            height={8}
          />
        ) : (
          <Image
            src={`image/ic_doc.svg`}
            width={8}
            height={8}
            alt={`${node.data.name}'s image`}
            className={dirIcFolder}
          />
        )}
        {node.isEditing ? (
          <input
            type="text"
            className={nodeNameInput}
            onKeyDown={handleKeyDown}
            autoFocus
          />
        ) : (
          node.data.name
        )}
      </div>
    </div>
  );
};
