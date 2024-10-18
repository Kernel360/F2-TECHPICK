package kernel360.techpick.feature.structure.service.node.server;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import kernel360.techpick.feature.structure.model.StructureDataProxy;
import kernel360.techpick.feature.structure.service.node.client.ClientNode;
import kernel360.techpick.feature.structure.service.node.client.FolderClientNode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FolderServerNode extends ServerNode {

	@NotNull
	private Long folderId;

	@NotNull
	private List<ServerNode> children;

	@Override
	public List<RelationalNode> toNodeList(Long parentFolderId) {
		List<RelationalNode> nodes = new ArrayList<>();
		nodes.add(new RelationalNode(folderId, parentFolderId, this.getType()));

		for (ServerNode child : children) {
			nodes.addAll(child.toNodeList(this.folderId));
		}
		return nodes;
	}

	@Override
	public ClientNode toClientNode(StructureDataProxy dataProxy) {

		List<ClientNode> childrenForClient = new ArrayList<>();
		for (ServerNode child : children) {
			childrenForClient.add(child.toClientNode(dataProxy));
		}

		return new FolderClientNode(
			this.getId(),
			this.getType(),
			dataProxy.findFolderById(folderId).getName(),
			folderId,
			childrenForClient
		);
	}
}
