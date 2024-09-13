import type { Meta, StoryObj } from '@storybook/react';
import { TestComponent } from './Test';

const meta = {
  title: 'Test',
  component: TestComponent,
  argTypes: {
    backgroundColor: { control: 'color' },
  },
  tags: ['autodocs'],
} satisfies Meta<typeof TestComponent>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Primary: Story = {};
