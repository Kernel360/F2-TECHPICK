package kernel360.techpick.feature.folder.service.dto;

import org.springframework.security.core.Authentication;

import lombok.Getter;

@Getter
public class FolderCreateRequest {
	private Long userId;
	private String name;

	public void updateUserId(Authentication auth) {
		this.userId = (Long)auth.getPrincipal();
	}
}
