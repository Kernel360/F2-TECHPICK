package kernel360.techpick.feature.folder.service.parser.action;

import kernel360.techpick.feature.folder.model.FolderStructureProvider;

public interface FolderAction {

	// run action on repository
	// TODO: 추후 Provider가 아닌 Service를 이용하도록 변경해야 함
	void run(FolderStructureProvider provider);
}
