export const filterCommandItems = (
  value: string,
  search: string,
  keywords?: string[] | undefined
): number => {
  const keywordString = keywords?.join(' ') ?? '';

  if (keywordString && keywordString.includes('생성')) {
    return 0.0001; // 낮은 우선순위
  }

  const extendValue = value + ' ' + keywordString;

  return extendValue.includes(search) ? 1 : 0;
};
