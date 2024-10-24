import { addNodeToStructure } from '@/components/nodeManagement/utils/addNodeToStructure';
import { getNewIdFromStructure } from '@/components/nodeManagement/utils/getNewIdFromStructure';
import { ApiStructureData } from '@/types/ApiTypes';
import { NodeData } from '@/types';

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
