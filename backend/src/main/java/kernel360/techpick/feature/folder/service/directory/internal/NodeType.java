package kernel360.techpick.feature.folder.service.directory.internal;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum NodeType {
	PICK("pick"),
	FOLDER("folder"),
	;

	private final String typeLabel;

	@JsonCreator
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
