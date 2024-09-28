package kernel360.techpick.feature.tag.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kernel360.techpick.core.model.tag.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

	boolean existsByUser_IdAndName(Long userId, String name);

	Optional<Tag> findByUser_IdAndName(Long userId, String name);

	List<Tag> findAllByUser_IdOrderByOrder(Long user_id);

	Optional<Tag> findFirstByUser_IdOrderByOrderDesc(Long user_id);

}
