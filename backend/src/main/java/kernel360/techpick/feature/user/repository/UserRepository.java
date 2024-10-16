package kernel360.techpick.feature.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kernel360.techpick.core.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsBySocialProviderId(String socialProviderId);

	Optional<User> findBySocialProviderId(String socialProviderId);
}
