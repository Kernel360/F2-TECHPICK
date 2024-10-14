import { useEffect, useState } from 'react';
import { HTTPError } from 'ky';
import { getLinkByUrl, GetLinkResponseType } from '@/entities/link';

export function useHasLink(url: string | undefined): UseHasLinkResponseType {
  const [isLoading, setIsLoading] = useState(true);
  const [data, setData] = useState<GetLinkResponseType>();

  useEffect(() => {
    const fetchHasLink = async (url: string) => {
      try {
        const linkData = await getLinkByUrl(url);
        setData(linkData);
        setIsLoading(false);
      } catch (error) {
        if (error instanceof HTTPError) {
          setIsLoading(false);
          return;
        }
      }
    };

    if (url) {
      fetchHasLink(url);
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
  | { isLoading: boolean; hasLink: true; data: GetLinkResponseType }
  | { isLoading: boolean; hasLink: false; data?: undefined };
