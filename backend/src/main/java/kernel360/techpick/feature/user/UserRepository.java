package kernel360.techpick.feature.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kernel360.techpick.core.feature.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	boolean existsBySocialProviderId(String socialProviderId);

	Optional<User> findBySocialProviderId(String socialProviderId);

}
