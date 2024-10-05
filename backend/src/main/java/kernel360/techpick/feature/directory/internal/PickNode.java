package kernel360.techpick.feature.directory.internal;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PickNode extends ClientNode {

	@NotNull
	private Long pickId;

	public List<RelationalNode> toNodeList(Long parentFolderId) {
		return List.of(
			new RelationalNode(pickId, parentFolderId, this.getType())
		);
	}
}
