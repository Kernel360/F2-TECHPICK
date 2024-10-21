package kernel360.techpick.core.model.folder;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder, Long> {

	List<Folder> findByParentFolder(Folder parentFolder);
}
