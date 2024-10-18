package kernel360.techpick.feature.structure.service.node.client;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import kernel360.techpick.feature.structure.service.node.common.NodeType;
import kernel360.techpick.feature.structure.service.node.server.RelationalNode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FolderClientNode extends ClientNode {

	@NotNull
	private Long folderId;

	@NotNull
	private List<ClientNode> children;

	public FolderClientNode(String id, NodeType type, String name, Long folderId, List<ClientNode> children) {
		super(id, type, name);
		this.folderId = folderId;
		this.children = children;
	}

    @Override
	public List<RelationalNode> toNodeList(Long parentFolderId) {
		List<RelationalNode> nodes = new ArrayList<>();
		nodes.add(new RelationalNode(folderId, parentFolderId, this.getType()));

		for (ClientNode child : children) {
			nodes.addAll(child.toNodeList(this.folderId));
		}
		return nodes;
	}
}
