package kernel360.techpick.feature.directory.internal;

public record RelationalNode(
	Long nodeId, // Folder Id or Pick Id
	Long parentFolderId, // if root, then parent folder id is...
	NodeType nodeType // Folder or Pick
) {}
