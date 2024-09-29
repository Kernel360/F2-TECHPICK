package kernel360.techpick.feature.tag.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kernel360.techpick.core.model.tag.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

	boolean existsByUserIdAndName(Long userId, String name);

	List<Tag> findAllByUserIdOrderByOrder(Long user_id);

	List<Tag> findAllByUserId(Long user_id);

	Optional<Tag> findFirstByUserIdOrderByOrderDesc(Long user_id);

}
