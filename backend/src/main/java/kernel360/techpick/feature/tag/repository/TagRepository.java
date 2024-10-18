package kernel360.techpick.feature.tag.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kernel360.techpick.core.model.tag.Tag;
import kernel360.techpick.core.model.user.User;

public interface TagRepository extends JpaRepository<Tag, Long> {

	boolean existsByUserAndName(User user, String name);

	List<Tag> findAllByUserOrderByTagOrder(User user);

	List<Tag> findAllByUser(User user);

	Optional<Tag> findFirstByUserOrderByTagOrderDesc(User user);

}
