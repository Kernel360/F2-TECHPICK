package kernel360.techpick.feature.tag.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kernel360.techpick.core.model.tag.Tag;
import kernel360.techpick.core.model.user.User;

public interface TagRepository extends JpaRepository<Tag, Long> {

	List<Tag> findAllByUser(User user);
}
