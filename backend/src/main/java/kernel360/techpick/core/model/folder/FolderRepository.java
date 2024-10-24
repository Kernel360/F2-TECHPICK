package kernel360.techpick.core.model.folder;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FolderRepository extends JpaRepository<Folder, Long> {

	List<Folder> findByParentFolderId(Long parentFolderId);

	// TODO: Field 함수는 MySQL계열에서만 제공되므로, 이후 QueryDSL로 리팩토링 필요
	@Query("select f from Folder f where f.id in :idList order by FIELD(f.id,:idList)")
	List<Folder> findAllByIdListOrdered(List<Long> idList);
}
