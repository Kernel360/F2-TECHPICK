package kernel360.techpick.feature.folder.service.parser.action;

import kernel360.techpick.feature.folder.model.FolderStructureProvider;

public class FolderMoveAction implements  FolderAction {
	private Long sourceFolderId; // 이동될 폴더 (자기 자신)
	private Long destinationFolderId; // 밑으로 들어 가려는 부모 폴더

	@Override
	public void run(FolderStructureProvider provider) {
		// TODO: modify folder table
	}
}
