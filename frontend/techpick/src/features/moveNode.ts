import { NodeData } from '@/shared/types';
import { NodeApi } from 'react-arborist';

export const moveNode = (
  data: NodeData[],
  focusedNode: NodeApi | null,
  focusedFolderNodeList: NodeApi[],
  focusedLinkNodeList: NodeApi[],
  setFocusedFolderNodeList: (node: NodeApi[]) => void,
  setFocusedLinkNodeList: (node: NodeApi[]) => void,
  dragId: string,
  dragNode: NodeApi,
  parentId: string | null,
  parentNode: NodeApi | null,
  targetIndex: number
): NodeData[] => {
  const updateFocusedFolderNodeList = () => {
    updateFocusedNodeList(focusedFolderNodeList, setFocusedFolderNodeList);
  };

  const updateFocusedLinkNodeList = () => {
    updateFocusedNodeList(focusedLinkNodeList, setFocusedLinkNodeList);
  };

  const updateFocusedNodeList = (
    nodeList: NodeApi[],
    setNodeList: (node: NodeApi[]) => void
  ) => {
    if (!nodeList) {
      return;
    }
    // 부모가 같은 위치로 이동할 경우
    if (dragNode.parent?.id === parentId) {
      const updatedNodeList = [...nodeList];
      const index = updatedNodeList.findIndex((node) => node.id === dragId);
      if (index !== -1) {
        updatedNodeList.splice(index, 1);
        updatedNodeList.splice(targetIndex, 0, dragNode);
        setNodeList(updatedNodeList);
      }
      return;
    }
    const updatedNodeList = [...nodeList];
    const index = updatedNodeList.findIndex((node) => node.id === dragId);
    // 포커스된 노드에서 드래그가 시작된 경우
    if (index !== -1) {
      updatedNodeList.splice(index, 1);
      setNodeList(updatedNodeList);
      console.log('updatedNodeList', updatedNodeList);
    }
    // 외부에서 포커스된 노드로 드래그한 경우
    if (focusedNode && parentNode?.id === focusedNode.id) {
      updatedNodeList.push(dragNode);
      setNodeList(updatedNodeList);
    }
  };

  const removeNode = (nodes: NodeData[]): NodeData[] =>
    nodes.reduce((acc: NodeData[], node) => {
      if (node.id === dragId) {
        return acc;
      }

      // 재귀적으로 자식 노드에서도 제거 수행
      if (node.children) {
        node.children = removeNode(node.children);
      }

      return [...acc, node]; // 제거되지 않은 노드를 결과 배열에 추가
    }, []);

  const insertNode = (nodes: NodeData[]): NodeData[] => {
    // 루트 레벨에 삽입하는 경우
    if (parentId === null) {
      const updatedRootNodes = [...nodes];

      // 같은 부모를 가지고 있으면서 아래로 이동할 경우
      if (
        dragNode.parent!.parent === null &&
        dragNode.rowIndex! <= targetIndex
      ) {
        // 제거 후 삽입하면 index가 1씩 밀리기 때문에 targetIndex 감소 처리
        targetIndex -= 1;
        console.log(true);
      }

      updatedRootNodes.splice(targetIndex, 0, dragNode.data);
      return updatedRootNodes;
    }

    // 자식 노드에 삽입하는 경우
    return nodes.map((node) => {
      if (node.id === parentId) {
        const updatedChildren = [...(node.children || [])];

        // 같은 부모를 가지고 있으면서 아래로 이동할 경우
        if (
          dragNode.parent!.id === parentId &&
          dragNode.rowIndex! - parentNode!.rowIndex! <= targetIndex
        ) {
          // 제거 후 삽입하면 index가 1씩 밀리기 때문에 targetIndex 감소 처리
          targetIndex -= 1;
        }

        updatedChildren.splice(targetIndex, 0, dragNode.data);
        return { ...node, children: updatedChildren };
      }

      // 재귀적으로 자식 노드에서 삽입 위치를 찾음
      if (node.children) {
        node.children = insertNode(node.children);
      }
      return node;
    });
  };

  if (dragNode.data.type === 'folder') {
    updateFocusedFolderNodeList();
  } else {
    updateFocusedLinkNodeList();
  }

  const updatedData = removeNode([...data]);
  return insertNode(updatedData);
};
