import { NodeApi, TreeApi } from 'react-arborist';
import { NodeData } from '@/types';

export const getCurrentTreeTypeByNode = (
  currentNode: NodeApi,
  treeRef: {
    rootRef: React.RefObject<TreeApi<NodeData> | undefined>;
    recycleBinRef: React.RefObject<TreeApi<NodeData> | undefined>;
  }
) => {
  return treeRef.recycleBinRef.current?.get(currentNode.id)
    ? 'recycleBin'
    : 'root';
};
