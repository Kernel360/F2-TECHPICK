import { NodeData } from '@/shared/types';

export function removeByIdFromStructure(
  structure: NodeData[],
  targetId: string
): NodeData[] {
  return structure.reduce<NodeData[]>((acc, node) => {
    if (node.id === targetId) {
      return acc;
    }

    const children = node.children
      ? removeByIdFromStructure(node.children, targetId)
      : [];

    acc.push({ ...node, children });
    return acc;
  }, []);
}
