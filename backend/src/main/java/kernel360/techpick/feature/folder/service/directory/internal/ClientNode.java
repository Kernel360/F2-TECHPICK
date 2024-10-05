package kernel360.techpick.feature.folder.service.directory.internal;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

/**
 * Reference: https://www.baeldung.com/jackson-deduction-based-polymorphism
 */
@Getter
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
	@JsonSubTypes.Type(value = FolderNode.class),
	@JsonSubTypes.Type(value = PickNode.class),
})
public abstract class ClientNode {

	@NotBlank
	private String id;

	@NotNull
	private NodeType type;

	@NotBlank
	private String name;

	public abstract List<RelationalNode> flatNode(Long parentFolderId);
}
