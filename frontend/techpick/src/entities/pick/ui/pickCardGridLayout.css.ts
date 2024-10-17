import { style } from '@vanilla-extract/css';
import { space } from 'techpick-shared';

export const pickCardGridLayoutStyle = style({
  display: 'grid',
  gridTemplateColumns: 'repeat(auto-fill, minmax(280px, 1fr))', // 280px는 PickCard의 width
  padding: space.md,
  rowGap: space.md,
  columnGap: space.md,
});
