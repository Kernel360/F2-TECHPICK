package kernel360.techpick.feature.folder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kernel360.techpick.core.model.folder.Folder;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {

	List<Folder> findAllByUserId(Long userId);

	List<Folder> findAllByUserIdAndParentId(Long userId, Long parentId);

	boolean existsByUserIdAndName(Long userId, String name);
}
