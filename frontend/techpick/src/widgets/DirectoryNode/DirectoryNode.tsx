import { DirectoryNodeProps } from '@/shared/types/NodeData';
import {
  dirIcFolder,
  dirName,
  dirNode,
  dirNodeWrapper,
  dirNodeWrapperFocused,
  nodeNameInput,
} from './DirectoryNode.css';
import Image from 'next/image';
import { ChevronRight, ChevronDown } from 'lucide-react';
import { useQueryClient } from '@tanstack/react-query';
import { useCreateFolder } from '@/features/nodeManagement/api/folder/useCreateFolder';
import { ApiStructureData } from '@/shared/types/ApiTypes';
import { useGetDefaultFolderData } from '@/features/nodeManagement/api/folder/useGetDefaultFolderData';
import { useMoveFolder } from '@/features/nodeManagement/api/folder/useMoveFolder';
import toast from 'react-hot-toast';
import { useTreeStore } from '@/shared/stores/treeStore';
import { getCurrentTreeTypeByNode } from '@/features/nodeManagement/utils/getCurrentTreeTypeByNode';
import { TreeContextMenu } from '@/widgets/ContextMenu/TreeContextMenu';

export const DirectoryNode = ({
  node,
  style,
  dragHandle,
}: DirectoryNodeProps) => {
  const queryClient = useQueryClient();
  const { setFocusedNode, treeRef } = useTreeStore();
  const { mutateAsync: createFolder } = useCreateFolder();
  const { mutateAsync: moveFolder } = useMoveFolder();
  const { data: defaultFolderIdData } = useGetDefaultFolderData();
  const cashedStructureData: ApiStructureData | undefined =
    queryClient.getQueryData(['rootAndRecycleBinData']);
  const realNodeId = node.data.folderId || node.data.pickId;
  const rootFolderId = defaultFolderIdData?.ROOT;
  const currentTree = getCurrentTreeTypeByNode(node, treeRef);
  const setRealNodeId = (id: number) => {
    if (node.data.folderId) node.data.folderId = id;
    else node.data.pickId = id;
  };

  const handleKeyDown = async (
    event: React.KeyboardEvent<HTMLInputElement>
  ) => {
    if (event.nativeEvent.isComposing) {
      return;
    }

    if (event.key === 'Escape') {
      if (node.data.folderId === -1) {
        await queryClient.invalidateQueries({
          queryKey: ['rootAndRecycleBinData'],
          exact: true,
        });
      } else node.reset();
    }

    if (event.key === 'Enter') {
      if (event.currentTarget.value === '') {
        toast.error('이름을 입력해주세요.');
        return;
      }

      if (realNodeId === -1) {
        // create 동작중 이름 입력인 경우(realId가 -1인 경우)
        // 폴더 생성 api 호출 (서버)
        // todo: error handling
        try {
          const newFolderData = await createFolder(event.currentTarget.value);
          console.log('Server: Folder created:', newFolderData);

          // 서버에서 생성한 폴더 아이디로 변경
          setRealNodeId(newFolderData.id);

          // 서버에 업데이트된 트리 전송
          const serverData = {
            parentFolderId: node.parent!.isRoot
              ? rootFolderId
              : node.parent!.data.folderId,
            structure: {
              root: cashedStructureData!.root,
              recycleBin: cashedStructureData!.recycleBin,
            },
          };
          console.log('serverData', serverData);
          // 폴더 이동 (서버)
          await moveFolder({
            folderId: newFolderData.id.toString(),
            structure: serverData,
          });
          await queryClient.invalidateQueries({
            queryKey: ['rootAndRecycleBinData'],
            exact: true,
          });
          node.reset();
        } catch (error) {
          console.error('Folder creation failed:', error);
          await queryClient.invalidateQueries({
            queryKey: ['rootAndRecycleBinData'],
            exact: true,
          });

          toast.error('이름이 중복됩니다.\n 다른 이름을 입력해주세요. ');
          node.reset();
        }
      } else node.submit(event.currentTarget.value);
    }
  };

  return (
    <div
      style={{ ...style }}
      className={node.isSelected ? dirNodeWrapperFocused : dirNodeWrapper}
      ref={dragHandle}
      onClick={() => {
        setFocusedNode(node);
        if (currentTree === 'root') {
          treeRef.recycleBinRef.current!.deselectAll();
          if (node.isLeaf) {
            console.log('node', node);
            // const pickData = getPickDetail(node.data.pickId!.toString());
            // pickData.then((data) => {
            //   alert(data.linkUrlResponse.url);
            // });
          }
        } else {
          treeRef.rootRef.current!.deselectAll();
        }
        node.toggle();
      }}
      onContextMenu={() => {
        setFocusedNode(node);
        node.select();
        if (currentTree === 'root') {
          treeRef.recycleBinRef.current!.deselectAll();
        } else {
          treeRef.rootRef.current!.deselectAll();
        }
      }}
    >
      {node.id !== '-2' && (
        <TreeContextMenu>
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
              <div className={dirName}>{node.data.name}</div>
            )}
          </div>
        </TreeContextMenu>
      )}
    </div>
  );
};
