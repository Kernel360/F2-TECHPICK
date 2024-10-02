package kernel360.techpick.feature.folder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kernel360.techpick.core.model.folder.FolderStructure;

@Repository
public interface FolderStructureRepository extends JpaRepository<FolderStructure, Long> {

	FolderStructure findByUserId(Long userId);
}
