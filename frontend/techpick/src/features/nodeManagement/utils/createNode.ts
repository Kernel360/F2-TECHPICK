import { NodeApi } from 'react-arborist';
import { NodeData } from '@/shared/types';
import { getNewIdFromStructure } from '@/features/nodeManagement/utils/getNewIdFromStructure';
import { ApiStructureData } from '@/shared/types/ApiTypes';

export const createNode = (
  structureData: ApiStructureData,
  type: 'internal' | 'leaf',
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

  const nextId = getNewIdFromStructure(structureData);

  const newFolder: NodeData = {
    id: nextId,
    folderId: newId,
    type: 'folder',
    name: newName,
    children: children,
  };

  const newPick: NodeData = {
    id: nextId,
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

  return insertNodeToData(structureData.root, newNode);
};
