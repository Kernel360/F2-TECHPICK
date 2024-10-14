import { NodeData } from '@/shared/types';

export function getNewIdFromStructure(structure: NodeData[]): string {
  const maxId = structure.reduce((acc, cur) => {
    const id = parseInt(cur.id, 10);
    return id > acc ? id : acc;
  }, 0);
  return String(maxId + 1);
}
