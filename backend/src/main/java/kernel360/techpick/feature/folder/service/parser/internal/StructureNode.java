package kernel360.techpick.feature.folder.service.parser.internal;

public record StructureNode(
	Long nodeId,
	Long parentFolderId,
	NodeType nodeType // Folder or Pick
) {}
