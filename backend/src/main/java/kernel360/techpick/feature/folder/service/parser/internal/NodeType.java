package kernel360.techpick.feature.folder.service.parser.internal;

import javax.lang.model.element.Element;

public enum NodeType {
	PICK("pick"),
	FOLDER("folder"),
	;

	private final String label;

	public static NodeType valueOfLabel(String label) {
		for (NodeType e : values()) {
			if (e.label.equals(label)) {
				return e;
			}
		}
		return null;
	}

	NodeType(String value) {
		this.label = value;
	}
}
