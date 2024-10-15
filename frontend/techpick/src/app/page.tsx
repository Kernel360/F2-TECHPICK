'use client';

import {
  DirectoryTreeSection,
  LinkEditorSection,
  FeaturedSection,
} from '@/widgets';
import { rootLayout } from '@/app/style.css';
import { viewContainer, viewWrapper } from './style.css';
import React, { useEffect, useMemo } from 'react';
import { NodeApi } from 'react-arborist';
import { useTreeStore } from '@/shared/stores/treeStore';
import { useRouter } from 'next/navigation';

function getCookie(name: string): string | undefined {
  const cookies = document.cookie.split(';');
  const cookie = cookies.find((row) => row.startsWith(`${name}`));
  return cookie ? decodeURIComponent(cookie.split('=')[1]) : undefined;
}

export default function MainPage() {
  const router = useRouter();
  const { focusedNode, setFocusedFolderNodeList, setFocusedLinkNodeList } =
    useTreeStore();
  const [tempFocusedFolderList, tempFocusedPickList] = useMemo(() => {
    if (!focusedNode || !focusedNode.children?.length) {
      return [[], []];
    }
    const folderList: NodeApi[] = [];
    const linkList: NodeApi[] = [];

    focusedNode.children?.forEach((node) => {
      if (node.data.type === 'folder') {
        folderList.push(node);
      } else if (node.data.type === 'pick') {
        linkList.push(node);
      }
    });

    return [folderList, linkList];
  }, [focusedNode]);

  useEffect(() => {
    setFocusedFolderNodeList(tempFocusedFolderList);
    setFocusedLinkNodeList(tempFocusedPickList);
  }, [
    tempFocusedFolderList,
    tempFocusedPickList,
    setFocusedFolderNodeList,
    setFocusedLinkNodeList,
  ]);

  useEffect(
    function loginValidation() {
      const techPickLogin = getCookie('techPickLogin');

      if (!techPickLogin) {
        alert('로그인이 필요합니다.');
        router.push('/login');
      }
    },
    [router]
  );

  return (
    <div className={rootLayout}>
      <div className={viewWrapper}>
        <div className={viewContainer}>
          <DirectoryTreeSection />
          <LinkEditorSection />
          <FeaturedSection />
        </div>
      </div>
    </div>
  );
}
