package kernel360.techpick.feature.directory.internal;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class FolderNode extends ClientNode {

	@NotNull
	private Long folderId;

	@NotNull
	private List<ClientNode> children;

	public List<RelationalNode> toNodeList(Long parentFolderId) {
		List<RelationalNode> nodes = new ArrayList<>();
		nodes.add(new RelationalNode(folderId, parentFolderId, this.getType()));

		for (ClientNode child : children) {
			nodes.addAll(child.toNodeList(this.folderId));
		}
		return nodes;
	}
}
