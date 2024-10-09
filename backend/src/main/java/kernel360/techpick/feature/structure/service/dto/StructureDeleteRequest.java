package kernel360.techpick.feature.structure.service.dto;

import org.springframework.security.core.Authentication;

import kernel360.techpick.feature.structure.service.Structure;
import kernel360.techpick.feature.structure.service.node.server.ServerNode;
import lombok.Getter;

@Getter
public class StructureDeleteRequest {

	private Long userId;
	private Long targetId;
	private Structure<ServerNode> structure;

	public void updateUserIdAndTargetId(Authentication auth, Long targetId) {
		this.userId = (Long)auth.getPrincipal();
		this.targetId = targetId;
	}
}
