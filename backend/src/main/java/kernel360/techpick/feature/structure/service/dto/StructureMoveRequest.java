package kernel360.techpick.feature.structure.service.dto;

import org.springframework.security.core.Authentication;

import kernel360.techpick.feature.structure.service.Structure;
import kernel360.techpick.feature.structure.service.node.server.ServerNode;
import lombok.Getter;

@Getter
public class StructureMoveRequest {

	private Long userId;
	private Long targetId;
	private Long rootId;
	private Long recycleBinId;
	private Long parentFolderId;
	private Structure<ServerNode> structure;

	public void updateUserIdAndTargetId(Authentication auth, Long targetId) {
		this.userId = (Long)auth.getPrincipal();
		this.targetId = targetId;
	}
}
