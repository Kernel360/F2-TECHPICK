package kernel360.techpick.core.model.tag;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import jakarta.persistence.LockModeType;

public interface TagRepository extends JpaRepository<Tag, Long> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	boolean existsByUserIdAndName(Long userId, String name);

	List<Tag> findAllByUserId(Long userId);

	void deleteByIdAndUserId(Long id, Long userId);
}
