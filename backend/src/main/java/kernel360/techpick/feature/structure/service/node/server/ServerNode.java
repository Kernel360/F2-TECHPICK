package kernel360.techpick.feature.structure.service.node.server;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kernel360.techpick.feature.structure.model.NameProvider;
import kernel360.techpick.feature.structure.service.node.client.ClientNode;
import kernel360.techpick.feature.structure.service.node.common.Node;
import kernel360.techpick.feature.structure.service.node.common.NodeType;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Reference: https://www.baeldung.com/jackson-deduction-based-polymorphism
 */
@Getter
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
	@JsonSubTypes.Type(value = FolderServerNode.class),
	@JsonSubTypes.Type(value = PickServerNode.class),
})
@JsonIgnoreProperties(value = {"name"}, allowGetters = true, ignoreUnknown = true)
public abstract class ServerNode implements Node {

	@NotBlank
	private String id;

	@NotNull
	private NodeType type;

	public abstract List<RelationalNode> toNodeList(Long parentFolderId);

	public abstract ClientNode toClientNode(NameProvider provider);
}
