import { NodeApi, TreeApi } from 'react-arborist';
import { NodeData } from '@/shared/types';

export const getCurrentTreeTypeByNode = (
  currentNode: NodeApi,
  treeRef: {
    rootRef: React.RefObject<TreeApi<NodeData> | undefined>;
    recycleBinRef: React.RefObject<TreeApi<NodeData> | undefined>;
  }
) => {
  console.log('currentNodeId:', currentNode.id);
  console.log('rootRef:', treeRef.rootRef.current);
  console.log(
    'get(currentNode.id):',
    treeRef.rootRef.current?.get(currentNode.id)
  );
  return treeRef.rootRef.current?.get(currentNode.id) ? 'root' : 'recycleBin';
};
