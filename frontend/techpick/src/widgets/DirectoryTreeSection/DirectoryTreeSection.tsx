import React, { useRef } from 'react';
import {
  directoryLabel,
  directoryLabelContainer,
  directoryTree,
  directoryTreeContainer,
  directoryTreeSectionFooter,
  directoryTreeWrapper,
  recycleBinTreeWrapperClosed,
  leftSidebarSection,
  logo,
  profileContainer,
  profileSection,
  recycleBinLabelContainer,
  recycleBinTreeWrapper,
  recycleBinContainerOpen,
  recycleBinContainerClosed,
  logout,
} from './DirectoryTreeSection.css';
import Image from 'next/image';
import { NodeData } from '@/shared/types/NodeData';
import { NodeApi, Tree, TreeApi } from 'react-arborist';
import useResizeObserver from 'use-resize-observer';
import { useDragDropManager } from 'react-dnd';
import { useTreeStore } from '@/shared/stores/treeStore';
import { Folder, LogOut, Trash2 } from 'lucide-react';
import { EditorContextMenu } from '../EditorContextMenu';
import { useGetRootAndRecycleBinStructure } from '@/features/nodeManagement/hooks/useGetRootAndRecycleBinStructure';
import { useTreeHandlers } from '@/features/nodeManagement/hooks/useTreeHandlers';
import { DirectoryNode } from '@/widgets/DirectoryNode/DirectoryNode';
import AddFolderPopoverButton from '@/widgets/DirectoryTreeSection/AddFolderPopoverButton';
import { useLogout } from '@/features/userManagement/hooks/useLogout';
import { useRouter } from 'next/navigation';

export function DirectoryTreeSection() {
  const { ref, width, height } = useResizeObserver<HTMLDivElement>();
  const treeRef = useRef<TreeApi<NodeData> | undefined>(undefined);
  const dragDropManager = useDragDropManager();
  const { setTreeRef, setFocusedNode, setNewNodeName } = useTreeStore();
  const { handleCreate, handleDrag, handleRename, handleDelete } =
    useTreeHandlers();
  const [isRecycleBinOpen, setIsRecycleBinOpen] = React.useState(false);

  const { mutate } = useLogout();
  const router = useRouter();

  const handleTreeRef = (instance: TreeApi<NodeData> | null | undefined) => {
    if (instance && !treeRef.current) {
      treeRef.current = instance;
      setTreeRef(treeRef);
    }
  };

  const handleLogout = async () => {
    mutate(undefined, {
      onSuccess: () => {
        console.log('Logout success');
        router.replace('/login');
      },
      onError: (error) => {
        console.error('Logout failed:', error);
        alert('Logout failed');
      },
    });
  };

  const {
    data: rootAndRecycleBinData,
    error: structureError,
    isLoading: isStructureLoading,
  } = useGetRootAndRecycleBinStructure();

  return (
    <div className={leftSidebarSection}>
      <div className={profileSection}>
        <div className={profileContainer}>
          <Image
            src={`image/ic_user.svg`}
            width={32}
            height={32}
            alt="profile"
          />
          <div className={logo}>TechPick</div>
        </div>
        <LogOut width={24} className={logout} onClick={handleLogout} />
      </div>
      <div className={directoryTreeContainer}>
        <div className={directoryLabelContainer}>
          <Folder size={20} strokeWidth={1} />
          <div className={directoryLabel}>Directory</div>
          <AddFolderPopoverButton
            onEditEnded={(name: string) => {
              setNewNodeName(name);
              setTimeout(() => {
                treeRef.current?.createInternal();
              }, 0);
            }}
          />
        </div>
        <div className={directoryTreeWrapper} ref={ref}>
          {isStructureLoading && <div>Loading...</div>}
          {structureError && <div>Error: {structureError.message}</div>}
          {!isStructureLoading && !structureError && (
            <EditorContextMenu>
              <Tree
                ref={handleTreeRef}
                className={directoryTree}
                data={rootAndRecycleBinData?.root}
                disableMultiSelection={true}
                onFocus={(node: NodeApi) => {
                  setFocusedNode(node);
                }}
                onMove={handleDrag}
                onCreate={handleCreate}
                onRename={handleRename}
                onDelete={handleDelete}
                openByDefault={false}
                width={width}
                height={height}
                rowHeight={32}
                indent={24}
                overscanCount={1}
                dndManager={dragDropManager}
              >
                {DirectoryNode}
              </Tree>
            </EditorContextMenu>
          )}
        </div>
        <div
          className={
            isRecycleBinOpen
              ? recycleBinContainerOpen
              : recycleBinContainerClosed
          }
        >
          <div
            className={recycleBinLabelContainer}
            onClick={() => {
              setIsRecycleBinOpen(!isRecycleBinOpen);
            }}
          >
            <Trash2 size={20} strokeWidth={1} />
            <div className={directoryLabel}>Recycle Bin</div>
          </div>
          <div
            className={
              isRecycleBinOpen
                ? recycleBinTreeWrapper
                : recycleBinTreeWrapperClosed
            }
          >
            <EditorContextMenu>
              <Tree
                className={directoryTree}
                data={rootAndRecycleBinData?.recycleBin}
                disableMultiSelection={true}
                onMove={handleDrag}
                onCreate={handleCreate}
                onRename={handleRename}
                onDelete={handleDelete}
                openByDefault={false}
                width={width}
                height={height}
                rowHeight={32}
                indent={24}
                overscanCount={1}
                dndManager={dragDropManager}
              >
                {DirectoryNode}
              </Tree>
            </EditorContextMenu>
          </div>
        </div>
      </div>
      <div className={directoryTreeSectionFooter}></div>
    </div>
  );
}
