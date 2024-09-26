package kernel360.techpick.feature.link.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kernel360.techpick.core.model.link.Link;

public interface LinkRepository extends JpaRepository<Link, Long> {

	boolean existsByUrl(String url);

	Optional<Link> findByUrl(String url);
}
