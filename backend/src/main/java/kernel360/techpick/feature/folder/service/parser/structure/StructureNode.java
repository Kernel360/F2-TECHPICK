package kernel360.techpick.feature.folder.service.parser.structure;

public record StructureNode(
	Long nodeId,
    Long parentFolderId,
	NodeType nodeType // Folder or Pick
) {}
