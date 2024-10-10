import { NodeData } from '@/shared/types/NodeData';

export interface DefaultFolderIdData {
  userId: number;
  RECYCLE_BIN: number;
  UNCLASSIFIED: number;
  ROOT: number;
}

export interface StructureData {
  root: NodeData[];
  recycleBin: NodeData[];
}
