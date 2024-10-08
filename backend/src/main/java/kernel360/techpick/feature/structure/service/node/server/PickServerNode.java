package kernel360.techpick.feature.structure.service.node.server;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import kernel360.techpick.feature.structure.model.NameProvider;
import kernel360.techpick.feature.structure.service.node.client.ClientNode;
import kernel360.techpick.feature.structure.service.node.client.PickClientNode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PickServerNode extends ServerNode {

	@NotNull
	private Long pickId;

	@Override
	public List<RelationalNode> toNodeList(Long parentFolderId) {
		return List.of(
			new RelationalNode(pickId, parentFolderId, this.getType())
		);
	}

	@Override
	public ClientNode toClientNode(NameProvider provider) {
		return new PickClientNode(
			this.getId(),
			this.getType(),
			// NOTE: 여기서 이름을 가져 옵니다.
			provider.findPickNameById(pickId),
			this.getPickId()
		);
	}
}
