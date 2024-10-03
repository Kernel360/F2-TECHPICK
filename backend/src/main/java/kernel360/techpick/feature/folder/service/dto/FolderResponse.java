package kernel360.techpick.feature.folder.service.dto;

public record FolderResponse(
	Long id,
	String name,
	Long parentId,
	Long userId
) {
}
