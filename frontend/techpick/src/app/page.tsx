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
import { getClientCookie } from '@/features/userManagement/utils/getClientCookie';
import { useGetDefaultFolderData } from '@/features/nodeManagement/api/folder/useGetDefaultFolderData';

export default function MainPage() {
  const router = useRouter();
  const { focusedNode, setFocusedFolderNodeList, setFocusedLinkNodeList } =
    useTreeStore();
  const { data: defaultFolderData, isLoading } = useGetDefaultFolderData();

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
      const techPickLogin = getClientCookie('techPickLogin');

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
        {!isLoading && (
          <div
            className={viewContainer}
            onContextMenu={(event) => {
              event.preventDefault();
            }}
          >
            <DirectoryTreeSection defaultFolderIdData={defaultFolderData!} />
            <LinkEditorSection />
            <FeaturedSection />
          </div>
        )}
      </div>
    </div>
  );
}
