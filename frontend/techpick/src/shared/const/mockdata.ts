import { NodeData } from '@/shared/types/NodeData';

export const mockData: NodeData[] = [
  {
    id: '1',
    name: 'Favorites',
    type: 'folder',
    children: null, // 명시적으로 null 처리
  },
  {
    id: '2',
    name: 'Frontend',
    type: 'folder',
    children: [
      {
        id: 'b1',
        name: 'React',
        type: 'folder',
        children: [
          { id: 'b1a', name: 'React Hooks', type: 'link', children: null },
          { id: 'b1b', name: 'React Router', type: 'link', children: null },
          { id: 'b1c', name: 'React Context', type: 'link', children: null },
        ],
      },
      {
        id: 'b2',
        name: 'TypeScript',
        type: 'folder',
        children: [
          {
            id: 'b2a',
            name: 'TypeScript Basics',
            type: 'link',
            children: null,
          },
          { id: 'b2b', name: 'Advanced Types', type: 'link', children: null },
        ],
      },
      {
        id: 'b3',
        name: 'CSS',
        type: 'folder',
        children: [
          { id: 'b3a', name: 'Flexbox', type: 'link', children: null },
          { id: 'b3b', name: 'Grid Layout', type: 'link', children: null },
        ],
      },
    ],
  },
  {
    id: '3',
    name: 'Backend',
    type: 'folder',
    children: [
      {
        id: 'c1',
        name: 'Node.js',
        type: 'folder',
        children: [
          { id: 'c1a', name: 'Express.js', type: 'link', children: null },
          { id: 'c1a', name: 'Express.js', type: 'folder', children: null },
          { id: 'c1b', name: 'Nest.js', type: 'link', children: null },
        ],
      },
      {
        id: 'c2',
        name: 'Databases',
        type: 'folder',
        children: [
          { id: 'c2a', name: 'MongoDB', type: 'link', children: null },
          { id: 'c2b', name: 'PostgreSQL', type: 'link', children: null },
        ],
      },
      {
        id: 'c3',
        name: 'Docker',
        type: 'folder',
        children: [
          { id: 'c3a', name: 'Docker Basics', type: 'link', children: null },
          { id: 'c3b', name: 'Docker Compose', type: 'link', children: null },
        ],
      },
    ],
  },
  {
    id: '4',
    name: 'Full Stack',
    type: 'folder',
    children: [
      { id: 'd1', name: 'React', type: 'folder', children: null },
      { id: 'd2', name: 'Node.js', type: 'folder', children: null },
      { id: 'd3', name: 'Databases', type: 'folder', children: null },
    ],
  },
];
