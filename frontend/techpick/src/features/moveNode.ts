import { NodeData } from '@/shared/types';

export const moveNode = (
  data: NodeData[],
  dragId: string,
  parentId: string | null,
  targetIndex: number
): NodeData[] => {
  console.log('moveNode()');
  let draggedNode: NodeData;
  let originalIndex: number;

  const removeNode = (nodes: NodeData[]): NodeData[] =>
    nodes.reduce((acc: NodeData[], node, index: number) => {
      if (node.id === dragId) {
        draggedNode = node;
        originalIndex = index;
        return acc;
      }

      // 재귀적으로 자식 노드에서도 제거 수행
      if (node.children) {
        node.children = removeNode(node.children);
      }
      return [...acc, node]; // 제거되지 않은 노드를 결과 배열에 추가
    }, []);

  const insertNode = (nodes: NodeData[]): NodeData[] => {
    if (!draggedNode || originalIndex === null) {
      throw new Error('Dragged node or original index is not set');
    }

    // 루트 레벨에 삽입하는 경우
    if (parentId === null || parentId === undefined) {
      const updatedRootNodes = [...nodes];
      if (originalIndex < targetIndex + 1) {
        targetIndex -= 1;
      }
      updatedRootNodes.splice(targetIndex, 0, draggedNode);
      return updatedRootNodes;
    }

    // 자식 노드에 삽입하는 경우
    return nodes.map((node) => {
      if (node.id === parentId) {
        const updatedChildren = [...(node.children || [])];
        if (originalIndex < targetIndex + 1) {
          targetIndex -= 1;
        }
        updatedChildren.splice(targetIndex, 0, draggedNode);
        return { ...node, children: updatedChildren };
      }

      // 재귀적으로 자식 노드에서 삽입 위치를 찾음
      if (node.children) {
        node.children = insertNode(node.children);
      }
      return node;
    });
  };

  const updatedData = removeNode([...data]);
  return insertNode(updatedData);
};
