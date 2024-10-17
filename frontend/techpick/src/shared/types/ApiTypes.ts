import { NodeData } from '@/shared/types/NodeData';

export interface ApiDefaultFolderIdData {
  userId: number;
  RECYCLE_BIN: number;
  UNCLASSIFIED: number;
  ROOT: number;
}

export interface ApiStructureData {
  root: NodeData[];
  recycleBin: NodeData[];
}

export interface ApiFolderData {
  id: number;
  name: string;
  parentFolderId: number;
  userId: number;
}
