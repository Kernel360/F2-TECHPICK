package kernel360.techpick.feature.folder.service.parser.internal;

import lombok.Builder;

@Builder
public record StructureNode(
	Long nodeId, // Folder Id or Pick Id
	Long parentFolderId, // if root, then parent folder id is...
	NodeType nodeType // Folder or Pick
) {}
