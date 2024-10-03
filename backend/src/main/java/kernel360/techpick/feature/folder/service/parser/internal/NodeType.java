package kernel360.techpick.feature.folder.service.parser.internal;

public enum NodeType {
	PICK("pick"),
	FOLDER("folder"),
	;

	private final String label;

	public static NodeType from(String label) throws IllegalNodeTypeException {
		for (NodeType e : values()) {
			if (e.label.equals(label)) {
				return e;
			}
		}
		throw new IllegalNodeTypeException();
	}

	NodeType(String value) {
		this.label = value;
	}
}
