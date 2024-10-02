package kernel360.techpick.feature.folder.service.parser.action;

import kernel360.techpick.feature.folder.model.FolderStructureProvider;

public class FolderDeleteAction implements FolderAction {
	private Long sourceFolderId; // 이동될 폴더 (자기 자신)

	@Override
	public void run(FolderStructureProvider provider) {
		// TODO: modify folder table
	}
}
