package kernel360.techpick.feature.structure.service.node.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum NodeType {
	PICK("pick"),
	FOLDER("folder"),
	;

	private final String typeLabel;

	@JsonValue // 직렬화
	public String getTypeLabel() {
		return typeLabel;
	}

	@JsonCreator // 비-직렬화
	public static NodeType from(String typeLabel) throws IllegalNodeTypeException {
		for (NodeType e : values()) {
			if (e.typeLabel.equals(typeLabel)) {
				return e;
			}
		}
		throw new IllegalNodeTypeException();
	}

	NodeType(String typeLabel) {
		this.typeLabel = typeLabel;
	}
}
