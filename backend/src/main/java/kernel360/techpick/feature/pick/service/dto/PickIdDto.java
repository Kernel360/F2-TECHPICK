package kernel360.techpick.feature.pick.service.dto;

import org.springframework.security.core.Authentication;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PickIdDto {

	private Long userId;
	private Long pickId;

	public static PickIdDto create(Authentication auth, Long pickId) {
		return new PickIdDto((Long)auth.getPrincipal(), pickId);
	}
}
