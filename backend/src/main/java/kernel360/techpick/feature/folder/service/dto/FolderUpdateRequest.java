package kernel360.techpick.feature.folder.service.dto;

public record FolderUpdateRequest(
	Long folderId,
	String name
) {}
