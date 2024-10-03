package kernel360.techpick.feature.folder.service.dto;

public record FolderUpdateRequest(
	Long id,
	String name,
	Long parentId
) {
}
