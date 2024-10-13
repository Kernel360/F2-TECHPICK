import { NodeData } from '@/shared/types';

export function addNodeToStructure(
  structure: NodeData[],
  parentId: string | null,
  index: number,
  newNodeData: NodeData
): NodeData[] {
  if (!parentId) {
    structure.splice(index, 0, newNodeData);
    console.log('AFTER: structure', structure);
    return structure;
  }
  return structure.map((node) => {
    if (node.id === parentId) {
      if (!node.children) {
        node.children = [];
      }
      node.children.splice(index, 0, newNodeData);
    } else if (node.children) {
      node.children = addNodeToStructure(
        node.children,
        parentId,
        index,
        newNodeData
      );
    }
    return node;
  });
}
