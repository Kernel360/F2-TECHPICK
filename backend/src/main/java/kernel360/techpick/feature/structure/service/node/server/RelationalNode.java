package kernel360.techpick.feature.structure.service.node.server;

import kernel360.techpick.feature.structure.service.node.common.NodeType;

public record RelationalNode(
	Long nodeId, // Folder Id or Pick Id
	Long parentFolderId, // if root, then parent folder id is...
	NodeType nodeType // Folder or Pick
) {}
