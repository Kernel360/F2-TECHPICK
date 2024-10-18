import { NodeData } from '@/shared/types';
import { addNodeToStructure } from '@/features/nodeManagement/utils/addNodeToStructure';
import { getNewIdFromStructure } from '@/features/nodeManagement/utils/getNewIdFromStructure';
import { ApiStructureData } from '@/shared/types/ApiTypes';

export function addRecycleBinToStructure(
  structure: ApiStructureData,
  recycleBinId: number,
  recycleBin: NodeData[]
) {
  const lastIndex = structure.root.length;
  const newId = getNewIdFromStructure(structure);
  const recycleBinNode: NodeData = {
    id: newId,
    type: 'folder',
    name: 'Recycle Bin',
    folderId: recycleBinId,
    children: recycleBin,
  };

  return addNodeToStructure(structure.root, null, lastIndex, recycleBinNode);
}
