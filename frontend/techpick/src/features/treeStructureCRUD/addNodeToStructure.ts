import { NodeData } from '@/shared/types';

export function addNodeToStructure(
  structure: NodeData[],
  parentId: string,
  index: number,
  newNode: NodeData
): NodeData[] {
  return structure.map((node) => {
    if (node.id === parentId) {
      return {
        ...node,
        children: [...(node.children || []), newNode]
          .slice(0, index)
          .concat([newNode])
          .concat(node.children?.slice(index) || []),
      };
    }

    if (node.children && node.children.length > 0) {
      return {
        ...node,
        children: addNodeToStructure(node.children, parentId, index, newNode),
      };
    }

    return node;
  });
}
