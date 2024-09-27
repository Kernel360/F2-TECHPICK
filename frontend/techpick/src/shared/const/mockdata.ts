import { NodeData } from '@/shared/types/NodeData';

let id = 1;

type Entry = {
  id: string;
  name: string;
  type: 'folder' | 'link';
  children?: Entry[];
};

const nextId = () => (id++).toString();
const link = (name: string): Entry => ({ name, id: nextId(), type: 'link' });
const folder = (name: string, ...children: Entry[]): Entry => ({
  name,
  id: nextId(),
  type: 'folder',
  children,
});

export const dynamicMockData = [
  folder('Favorites'),
  folder(
    'Frontend',
    folder(
      'React',
      link('React Hooks'),
      link('React Router'),
      link('React Context')
    ),
    folder('TypeScript', link('TypeScript Basics'), link('Advanced Types')),
    folder('CSS', link('Flexbox'), link('Grid Layout'))
  ),
  folder(
    'Backend',
    folder('Node.js', link('Express.js'), link('Nest.js')),
    folder('Databases', link('MongoDB'), link('PostgreSQL')),
    folder('Docker', link('Docker Basics'), link('Docker Compose'))
  ),
  folder('Full Stack', folder('React'), folder('Node.js'), folder('Databases')),
];

export const mockData: NodeData[] = [
  {
    id: '1',
    name: 'Favorites',
    type: 'folder',
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
          { id: 'b1a', name: 'React Hooks', type: 'link' },
          { id: 'b1b', name: 'React Router', type: 'link' },
          { id: 'b1c', name: 'React Context', type: 'link' },
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
          },
          { id: 'b2b', name: 'Advanced Types', type: 'link' },
        ],
      },
      {
        id: 'b3',
        name: 'CSS',
        type: 'folder',
        children: [
          { id: 'b3a', name: 'Flexbox', type: 'link' },
          { id: 'b3b', name: 'Grid Layout', type: 'link' },
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
          { id: 'c1a', name: 'Express.js', type: 'link' },
          { id: 'c1a', name: 'Express.js', type: 'folder' },
          { id: 'c1b', name: 'Nest.js', type: 'link' },
        ],
      },
      {
        id: 'c2',
        name: 'Databases',
        type: 'folder',
        children: [
          { id: 'c2a', name: 'MongoDB', type: 'link' },
          { id: 'c2b', name: 'PostgreSQL', type: 'link' },
        ],
      },
      {
        id: 'c3',
        name: 'Docker',
        type: 'folder',
        children: [
          { id: 'c3a', name: 'Docker Basics', type: 'link' },
          { id: 'c3b', name: 'Docker Compose', type: 'link' },
        ],
      },
    ],
  },
  {
    id: '4',
    name: 'Full Stack',
    type: 'folder',
    children: [
      { id: 'd1', name: 'React', type: 'folder' },
      { id: 'd2', name: 'Node.js', type: 'folder' },
      { id: 'd3', name: 'Databases', type: 'folder' },
    ],
  },
];
