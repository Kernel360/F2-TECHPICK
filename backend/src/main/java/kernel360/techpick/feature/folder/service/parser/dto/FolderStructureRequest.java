package kernel360.techpick.feature.folder.service.parser.dto;

import kernel360.techpick.feature.folder.service.parser.action.FolderAction;

public record FolderStructureRequest(
	String jsonHierarchy, // raw json --> will be parsed by Hierarchy Parser
	FolderAction action // create, move, delete
) {}
