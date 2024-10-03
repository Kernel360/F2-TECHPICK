package kernel360.techpick.feature.folder.service.dto;

public record FolderResponse(
	Long id,
	String name,
	Long parentFolderId,
	Long userId
) {
}
