import { useEffect, useState } from 'react';
import { HTTPError } from 'ky';
import { getPickByUrl, PickTypes } from '@/entities/pick';

export function useHasPick(url: string | undefined): UseHasLinkResponseType {
  const [isLoading, setIsLoading] = useState(true);
  const [data, setData] = useState<PickTypes.GetPickResponseType>();

  useEffect(() => {
    const fetchHasPick = async (url: string) => {
      try {
        const pickData = await getPickByUrl(url);
        setData(pickData);
        setIsLoading(false);
      } catch (error) {
        if (error instanceof HTTPError) {
          setIsLoading(false);
          return;
        }
      }
    };

    if (url && url !== '') {
      fetchHasPick(url);
    }
  }, [url]);

  if (isLoading) {
    return { isLoading: true, hasLink: false };
  }

  if (data) {
    return { isLoading: false, hasLink: true, data };
  }

  return { isLoading: false, hasLink: false };
}

type UseHasLinkResponseType =
  | { isLoading: boolean; hasLink: true; data: PickTypes.GetPickResponseType }
  | { isLoading: boolean; hasLink: false; data?: undefined };
