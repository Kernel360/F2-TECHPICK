import React, { useRef } from 'react';
import {
  directoryLabel,
  directoryLabelContainer,
  directoryTree,
  directoryTreeContainer,
  directoryTreeSectionFooter,
  directoryTreeWrapper,
  directoryTreeWrapperFullSize,
  leftSidebarSection,
  logo,
  logout,
  plusButton,
  profileContainer,
  profileSection,
  recycleBinContainerClosed,
  recycleBinContainerOpen,
  recycleBinLabelContainer,
  recycleBinTreeWrapper,
  recycleBinTreeWrapperClosed,
  rightIcon,
  unClassifiedLabelContainer,
} from './DirectoryTreeSection.css';
import Image from 'next/image';
import { NodeData } from '@/shared/types/NodeData';
import { NodeApi, Tree, TreeApi } from 'react-arborist';
import useResizeObserver from 'use-resize-observer';
import { useDragDropManager } from 'react-dnd';
import { useTreeStore } from '@/shared/stores/treeStore';
import {
  ChevronRight,
  CircleAlert,
  Folder,
  LogOut,
  Plus,
  Trash2,
} from 'lucide-react';
import { useGetRootAndRecycleBinData } from '@/features/nodeManagement/api/folder/useGetRootAndRecycleBinData';
import { useTreeHandlers } from '@/features/nodeManagement/hooks/useTreeHandlers';
import { DirectoryNode } from '@/widgets/DirectoryNode/DirectoryNode';
import { useLogout } from '@/features/userManagement/api/useLogout';
import { useRouter } from 'next/navigation';
import toast from 'react-hot-toast';
import { convertPickDataToNodeData } from '@/features/nodeManagement/utils/convertPickDataToNodeData';
import { addNodeToStructure } from '@/features/nodeManagement/utils/addNodeToStructure';
import { useQueryClient } from '@tanstack/react-query';
import {
  ApiDefaultFolderIdData,
  ApiStructureData,
} from '@/shared/types/ApiTypes';
import { debounce } from 'lodash';
import { useGetPicksByParentId } from '@/features/nodeManagement/api/pick/useGetPicksByParentId';

export function DirectoryTreeSection({
  defaultFolderIdData,
}: {
  defaultFolderIdData: ApiDefaultFolderIdData;
}) {
  const queryClient = useQueryClient();
  const { ref, width, height } = useResizeObserver<HTMLDivElement>();
  const rootTreeRef = useRef<TreeApi<NodeData> | undefined>(undefined);
  const recycleBinTreeRef = useRef<TreeApi<NodeData> | undefined>(undefined);
  const dragDropManager = useDragDropManager();
  const {
    treeRef,
    setTreeRef,
    setFocusedNode,
    setUnClassifiedPickDataList,
    setUnclassifiedNodeRoot,
  } = useTreeStore();
  const {
    handleCreate,
    handleDrag,
    handleRename,
    handleMoveToTrash,
    handleDelete,
  } = useTreeHandlers();
  const [isRecycleBinOpen, setIsRecycleBinOpen] = React.useState(false);

  const { mutate } = useLogout();
  const router = useRouter();

  const handleTreeRef =
    (type: 'root' | 'recycleBin') =>
    (instance: TreeApi<NodeData> | null | undefined) => {
      if (!instance) {
        return;
      }

      if (type === 'root' && !rootTreeRef.current) {
        rootTreeRef.current = instance;
        setTreeRef(rootTreeRef, recycleBinTreeRef);
      }

      if (type === 'recycleBin' && !recycleBinTreeRef.current) {
        recycleBinTreeRef.current = instance;
        setTreeRef(rootTreeRef, recycleBinTreeRef);
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
        toast.error('Logout failed');
      },
    });
  };

  const {
    data: rootAndRecycleBinData,
    error: structureError,
    isLoading: isStructureLoading,
    refetch: refetchStructure,
  } = useGetRootAndRecycleBinData();

  const {
    data: unClassifiedPickDataList,
    isLoading: isUnClassifiedPickDataLoading,
    refetch: refetchUnclassifiedPickDataList,
  } = useGetPicksByParentId(defaultFolderIdData.UNCLASSIFIED.toString());

  function convertUnClassifiedPickDataToNodeApi() {
    if (unClassifiedPickDataList && rootAndRecycleBinData) {
      // NodeData 타입으로 변환해야함
      const unClassifiedNodeData = convertPickDataToNodeData(
        unClassifiedPickDataList,
        rootAndRecycleBinData
      );
      //  Tree에 추가해야 함
      const newRootData = addNodeToStructure(
        rootAndRecycleBinData.root,
        null,
        0,
        unClassifiedNodeData
      );
      queryClient.setQueryData(
        ['rootAndRecycleBinData'],
        (oldData: ApiStructureData) => ({
          root: newRootData,
          recycleBin: oldData.recycleBin,
        })
      );
      // NodeApi 타입으로 변환된 데이터를 Store 에 저장, focusedNode 설정
      setTimeout(() => {
        const unClassifiedNodeRoot = rootTreeRef.current?.get('-2');
        if (unClassifiedNodeRoot) {
          setUnclassifiedNodeRoot(unClassifiedNodeRoot);
          setFocusedNode(unClassifiedNodeRoot);
        }
      }, 0);
      // Tree에서 삭제
      refetchStructure();
    }
  }

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
        <div
          className={unClassifiedLabelContainer}
          onClick={debounce(async () => {
            await refetchUnclassifiedPickDataList();
            if (!unClassifiedPickDataList) {
              return;
            }
            treeRef.rootRef.current?.deselectAll();
            treeRef.recycleBinRef.current?.deselectAll();
            setUnClassifiedPickDataList(unClassifiedPickDataList);

            convertUnClassifiedPickDataToNodeApi();
          }, 300)}
        >
          <CircleAlert size={20} strokeWidth={1} />
          <div className={directoryLabel}>Unclassified</div>
          <ChevronRight className={rightIcon} size={20} strokeWidth={1.3} />
        </div>
        <div className={directoryLabelContainer}>
          <Folder size={20} strokeWidth={1} />
          <div className={directoryLabel}>Directory</div>
          <Plus
            className={plusButton}
            width={20}
            strokeWidth={1.3}
            onClick={() => {
              if (rootTreeRef.current?.isEditing) {
                return;
              }
              rootTreeRef.current?.create({
                type: 'internal',
                parentId: rootTreeRef.current?.focusedNode?.id,
                index: 0,
              });
            }}
          />
        </div>
        <div
          className={
            isRecycleBinOpen
              ? directoryTreeWrapper
              : directoryTreeWrapperFullSize
          }
          ref={ref}
        >
          {isStructureLoading && isUnClassifiedPickDataLoading && (
            <div>Loading...</div>
          )}

          {structureError && <div>Error: {structureError.message}</div>}
          {!isStructureLoading &&
            !isUnClassifiedPickDataLoading &&
            !structureError && (
              <Tree<NodeData>
                ref={handleTreeRef('root')}
                className={directoryTree}
                data={rootAndRecycleBinData?.root}
                disableMultiSelection={true}
                onFocus={(node: NodeApi) => {
                  setFocusedNode(node);
                }}
                onMove={handleDrag}
                onCreate={handleCreate}
                onRename={handleRename}
                onDelete={handleMoveToTrash}
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
            <Tree<NodeData>
              ref={handleTreeRef('recycleBin')}
              className={directoryTree}
              data={rootAndRecycleBinData?.recycleBin}
              disableMultiSelection={true}
              disableDrag={true}
              disableDrop={true}
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
          </div>
        </div>
      </div>
      <div className={directoryTreeSectionFooter}></div>
    </div>
  );
}
