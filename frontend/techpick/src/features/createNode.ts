import { NodeApi, TreeApi } from 'react-arborist';
import { NodeData } from '@/shared/types';

export const createNode = (
  treeData: NodeData[],
  focusedNode: NodeApi | null,
  type: 'internal' | 'leaf',
  treeApi: TreeApi<NodeData> | undefined,
  parentId: string | null,
  parentNode: NodeApi | null,
  index: number,
  newId: number,
  newName: string
) => {
  let children;
  if (type === 'internal') {
    children = [];
  }

  const nextId = () => {
    const maxId = treeData.reduce((acc, cur) => {
      const id = parseInt(cur.id, 10);
      return id > acc ? id : acc;
    }, 0);
    return String(maxId + 1);
  };

  const newFolder: NodeData = {
    id: nextId(),
    folderId: newId,
    type: 'folder',
    name: newName,
    children: children,
  };

  const newPick: NodeData = {
    id: nextId(),
    pickId: newId,
    type: 'pick',
    name: newName,
  };

  const newNode = type === 'internal' ? newFolder : newPick;

  console.log('Client : newNode', newNode);

  const insertNodeToData = (dataList: NodeData[], newNode: NodeData) => {
    if (!parentNode) {
      const updatedDataList = [...dataList];
      updatedDataList.splice(index, 0, newNode);
      return updatedDataList;
    }

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
        data.children = insertNodeToData(data.children, newNode);
      }

      return data;
    });
  };

  return insertNodeToData(treeData, newNode);
};
