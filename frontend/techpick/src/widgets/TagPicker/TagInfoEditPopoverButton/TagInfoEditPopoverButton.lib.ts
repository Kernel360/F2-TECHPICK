export const isEmptyString = (value: string) => {
  return value === '';
};

export const isSameValue = <T>(value1: T, value2: T) => {
  return value1 === value2;
};
