import { ApiPickData, ApiStructureData } from '@/shared/types/ApiTypes';
import { getNewIdFromStructure } from '@/features/nodeManagement/utils/getNewIdFromStructure';
import { NodeData } from '@/shared/types';

export function convertPickDataToNodeData(
  unClassifiedPickDataList: ApiPickData[],
  structure: ApiStructureData
): NodeData {
  let newId = Number(getNewIdFromStructure(structure));
  const children: NodeData[] = unClassifiedPickDataList.map((pickData) => {
    return {
      id: String(newId++),
      name: pickData.title,
      type: 'pick',
      pickId: pickData.id,
      url: pickData.linkUrlResponse.url,
    };
  });
  return {
    id: '-2',
    name: '미분류',
    type: 'folder',
    folderId: -2,
    children,
  };
}
