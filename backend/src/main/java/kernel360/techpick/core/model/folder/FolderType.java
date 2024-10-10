package kernel360.techpick.core.model.folder;

import java.util.EnumSet;

public enum FolderType {

	UNCLASSIFIED,
	RECYCLE_BIN,
	GENERAL,
	ROOT,

	;

	public static EnumSet<FolderType> getBasicFolderTypes() {
		return EnumSet.of(UNCLASSIFIED, RECYCLE_BIN, ROOT);
	}

	public static EnumSet<FolderType> getUnclassifiedFolderTypes() {
		return EnumSet.of(GENERAL, RECYCLE_BIN, ROOT);
	}
}
