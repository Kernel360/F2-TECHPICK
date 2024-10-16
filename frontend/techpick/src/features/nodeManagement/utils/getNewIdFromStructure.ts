import { NodeData } from '@/shared/types';
import { ApiStructureData } from '@/shared/types/ApiTypes';

function getMaxIdFromNodes(nodes: NodeData[]): number {
  let maxId = 0;

  const traverse = (nodeList: NodeData[]) => {
    for (const node of nodeList) {
      const id = parseInt(node.id, 10);
      if (!isNaN(id)) {
        maxId = Math.max(maxId, id);
      }
      if (node.children && node.children.length > 0) {
        traverse(node.children);
      }
    }
  };
  traverse(nodes);
  return maxId;
}

export function getNewIdFromStructure(structure: ApiStructureData): string {
  const maxIdInRoot = getMaxIdFromNodes(structure.root);
  const maxIdInRecycleBin = getMaxIdFromNodes(structure.recycleBin);
  const maxId = Math.max(maxIdInRoot, maxIdInRecycleBin);

  return String(maxId + 1);
}
