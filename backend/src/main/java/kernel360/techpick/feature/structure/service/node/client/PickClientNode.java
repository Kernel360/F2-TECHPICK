package kernel360.techpick.feature.structure.service.node.client;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import kernel360.techpick.feature.structure.service.node.common.NodeType;
import kernel360.techpick.feature.structure.service.node.server.RelationalNode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PickClientNode extends ClientNode {

	@NotNull
	private Long pickId;

	@Override
	public List<RelationalNode> toNodeList(Long parentFolderId) {
		return List.of(
			new RelationalNode(pickId, parentFolderId, this.getType())
		);
	}

	public PickClientNode(String id, NodeType type, String name, Long pickId) {
		super(id, type, name);
		this.pickId = pickId;
	}
}
