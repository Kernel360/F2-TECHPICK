package kernel360.techpick.feature.folder.service.dto;

public record FolderCreateDto(
	Long userId,
	String name,
	Long parentFolderId
) {
}
