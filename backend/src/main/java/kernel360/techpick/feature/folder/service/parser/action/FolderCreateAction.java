package kernel360.techpick.feature.folder.service.parser.action;

import kernel360.techpick.feature.folder.model.FolderStructureProvider;

public class FolderCreateAction implements FolderAction {
	private Long destinationFolderId; // 밑으로 들어 가려는 부모 폴더

	@Override
	public void run(FolderStructureProvider provider) {
		// TODO: modify folder table
	}
}
