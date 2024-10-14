import { NodeData } from '@/shared/types';

export function removeByIdFromStructure(
  structure: NodeData[],
  targetId: string
): NodeData[] {
  for (let i = 0; i < structure.length; i++) {
    const node = structure[i];

    if (node.id === targetId) {
      structure.splice(i, 1);
      return structure;
    }

    if (node.children && node.children.length > 0) {
      node.children = removeByIdFromStructure(node.children, targetId);
    }
  }

  return structure;
}
