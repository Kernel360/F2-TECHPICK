package kernel360.techpick.feature.folder.service.dto;

public record FolderMoveRequest(
	Long id,
	String name,
	Long parentFolderId
) {
}
