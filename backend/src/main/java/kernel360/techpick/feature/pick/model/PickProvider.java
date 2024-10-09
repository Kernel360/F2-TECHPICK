package kernel360.techpick.feature.pick.model;

import java.util.List;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.pick.Pick;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.pick.exception.ApiPickException;
import kernel360.techpick.feature.pick.repository.PickRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PickProvider {

	private final PickRepository pickRepository;

	public Pick findById(Long pickId) {
		return pickRepository.findById(pickId).orElseThrow(ApiPickException::PICK_NOT_FOUND);
	}

	public List<Pick> findAllByUser(User user) {
		return pickRepository.findAllByUser(user);
	}

	public void deleteById(Long pickId) {
		pickRepository.deleteById(pickId);
	}
}
