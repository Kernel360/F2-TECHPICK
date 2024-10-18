package kernel360.techpick.feature.structure.service.node.client;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kernel360.techpick.feature.structure.service.node.common.Node;
import kernel360.techpick.feature.structure.service.node.common.NodeType;
import kernel360.techpick.feature.structure.service.node.server.RelationalNode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Reference: https://www.baeldung.com/jackson-deduction-based-polymorphism
 */
@Getter
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
	@JsonSubTypes.Type(value = FolderClientNode.class),
	@JsonSubTypes.Type(value = PickClientNode.class),
})
public abstract class ClientNode implements Node {

	@NotBlank
	@Getter
	private String id;

	@NotNull
	@Getter
	private NodeType type;

	private String name;

	public ClientNode(String id, NodeType type, String name) {
		this.id = id;
		this.type = type;
		this.name = name;
	}

	public abstract List<RelationalNode> toNodeList(Long parentFolderId);
}
