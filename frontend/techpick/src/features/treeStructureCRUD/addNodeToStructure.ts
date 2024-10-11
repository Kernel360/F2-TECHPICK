import { NodeData } from '@/shared/types';

export function addNodeToStructure(
  structure: NodeData[],
  parentId: string | null,
  index: number,
  newNode: NodeData
): NodeData[] {
  if (!parentId) {
    structure.splice(index, 0, newNode);
    console.log('AFTER: structure', structure);
    return structure;
  }
  return structure.map((node) => {
    if (node.id === parentId) {
      if (!node.children) {
        node.children = [];
      }
      node.children.splice(index, 0, newNode);
    } else if (node.children) {
      node.children = addNodeToStructure(
        node.children,
        parentId,
        index,
        newNode
      );
    }
    return node;
  });
}
