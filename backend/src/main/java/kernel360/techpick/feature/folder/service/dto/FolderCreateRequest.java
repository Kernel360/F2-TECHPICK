package kernel360.techpick.feature.folder.service.dto;

public record FolderCreateRequest(
	String name,
	Long parentId
) {
}
