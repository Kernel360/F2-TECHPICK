package kernel360.techpick.feature.folder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kernel360.techpick.core.model.folder.Folder;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {

	List<Folder> findAllByUserId(Long userId);

	List<Folder> findAllByUserIdAndParentFolderId(Long userId, Long parentFolderId);

	// QueryDSL 도입 후 리팩토링 필요
	@Query("SELECT f FROM Folder f WHERE f.user.id = :userId AND f.folderType = kernel360.techpick.core.model.folder.FolderType.UNCLASSIFIED")
	Folder findUnclassifiedByUserId(@Param("userId") Long userId);

	// QueryDSL 도입 후 리팩토링 필요
	@Query("SELECT f FROM Folder f WHERE f.user.id = :userId AND f.folderType = kernel360.techpick.core.model.folder.FolderType.RECYCLE_BIN")
	Folder findRecycleBinByUserId(Long userId);

	// QueryDSL 도입 후 리팩토링 필요
	@Query("SELECT f FROM Folder f WHERE f.user.id = :userId AND f.folderType = kernel360.techpick.core.model.folder.FolderType.ROOT")
	Folder findRootByUserId(Long userId);

	boolean existsByUserIdAndName(Long userId, String name);
}
