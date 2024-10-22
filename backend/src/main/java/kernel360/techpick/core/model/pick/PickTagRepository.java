package kernel360.techpick.core.model.pick;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PickTagRepository extends JpaRepository<PickTag, Long> {

	void deleteByPick(Pick pick);

	void deleteByTagId(Long tagId);

	void deleteByPickAndTagId(Pick pick, Long tagId);
}
