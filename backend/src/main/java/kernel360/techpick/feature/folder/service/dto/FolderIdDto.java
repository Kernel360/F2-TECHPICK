package kernel360.techpick.feature.folder.service.dto;

import org.springframework.security.core.Authentication;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FolderIdDto {

	private Long userId;
	private Long folderId;

	public static FolderIdDto of(Authentication auth, Long folderId) {
		return new FolderIdDto((Long)auth.getPrincipal(), folderId);
	}
}
