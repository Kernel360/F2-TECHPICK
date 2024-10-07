package kernel360.techpick.feature.folder.service.dto;

public record FolderMoveDto(
	Long userId,
	Long id,
	String name,
	Long parentFolderId
) {
}
