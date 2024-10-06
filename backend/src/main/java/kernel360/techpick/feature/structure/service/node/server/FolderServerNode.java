package kernel360.techpick.feature.structure.service.node.server;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import kernel360.techpick.feature.structure.service.node.client.ClientNode;
import kernel360.techpick.feature.structure.service.node.client.FolderClientNode;
import kernel360.techpick.feature.structure.service.model.NameProvider;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FolderServerNode extends ServerNode {

	@NotNull
	private Long folderId;

	@NotNull
	private List<ClientNode> children;

	@Override
	public List<RelationalNode> toNodeList(Long parentFolderId) {
		List<RelationalNode> nodes = new ArrayList<>();
		nodes.add(new RelationalNode(folderId, parentFolderId, this.getType()));

		for (ClientNode child : children) {
			nodes.addAll(child.toNodeList(this.folderId));
		}
		return nodes;
	}

	@Override
	public ClientNode toClientNode(NameProvider provider) {
		return new FolderClientNode(
			this.getId(),
			this.getType(),
			// NOTE: 여기서 이름을 가져 옵니다.
			provider.findFolderNameById(folderId),
			this.getFolderId(),
			this.getChildren()
		);
	}
}
