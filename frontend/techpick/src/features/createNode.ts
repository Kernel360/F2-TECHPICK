import { NodeApi } from 'react-arborist';
import { NodeData } from '@/shared/types';

export const createNode = (
  treeData: NodeData[],
  focusedNode: NodeApi | null,
  createNodeType: 'folder' | 'pick',
  parentId: string | null,
  parentNode: NodeApi | null,
  index: number
) => {
  const newId = '999';
  let children;
  if (createNodeType === 'folder') {
    children = [];
  }

  const newNode = {
    id: newId,
    type: createNodeType,
    name: 'New Node',
    children: children,
  };
  console.log('newNode', newNode);

  const insertNode = (dataList: NodeData[], newNode: NodeData) => {
    if (parentId === null) {
      const updatedDataList = [...dataList];
      return updatedDataList.splice(index, 0, newNode);
    }

    return dataList.map((data) => {
      if (data.id === parentId) {
        const updatedChildren = [...(data.children || [])];

        updatedChildren.splice(index, 0, newNode);
        return { ...data, children: updatedChildren };
      }

      if (data.children) {
        data.children = insertNode(data.children, newNode);
      }

      return data;
    });
  };

  return insertNode(treeData, newNode);
};
