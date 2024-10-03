package kernel360.techpick.feature.folder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.core.model.folder.FolderType;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {

	List<Folder> findAllByUserId(Long userId);

	List<Folder> findAllByUserIdAndParentId(Long userId, Long parentId);

	Folder findByUserIdAndFolderType(Long userId, FolderType folderType);

	boolean existsByUserIdAndName(Long userId, String name);
}
