package kernel360.techpick.feature.structure.validator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.feature.structure.exception.ApiStructureException;
import kernel360.techpick.feature.structure.model.StructureDataProxy;
import kernel360.techpick.feature.structure.service.Structure;
import kernel360.techpick.feature.structure.service.node.common.NodeType;
import kernel360.techpick.feature.structure.service.node.server.RelationalNode;
import kernel360.techpick.feature.structure.service.node.server.ServerNode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class StructureValidator {

	public void validate(Structure<ServerNode> structure, StructureDataProxy dataProxy, Long rootId, Long recycleBinId) {

		List<RelationalNode> nodeList = new ArrayList<>();
		nodeList.addAll(structure.convertRootToNodeList(rootId));
		nodeList.addAll(structure.convertRecycleBinToNodeList(recycleBinId));

		validateNodeSize(nodeList, dataProxy);
		if (!validateNodeList(nodeList, dataProxy)) {
			throw ApiStructureException.INVALID_JSON_STRUCTURE();
		}
	}

	private void validateNodeSize(List<RelationalNode> nodeList, StructureDataProxy dataProxy) {

		int nodeSize = nodeList.size();
		int expected = dataProxy.folderListSize() + dataProxy.pickListSize();
		if (nodeSize != expected) {
			log.error(
				"노드 개수가 일치하지 않습니다 root's child node count:{}, expected count: [folder {} + pick {}]"
				, nodeSize, dataProxy.folderListSize(), dataProxy.pickListSize()
			);
			throw ApiStructureException.INVALID_JSON_STRUCTURE();
		}
	}

	// TODO: need refactor
	private boolean validateNodeList(List<RelationalNode> nodeList, StructureDataProxy dataProxy) {

		for (var node : nodeList) {
			// Validate Folder List
			if (node.nodeType() == NodeType.FOLDER) {
				if (!dataProxy.containsFolder(node.nodeId())) {
					log.error("[FOLDER] 존재하지 않는 폴더 folderId : {}", node.nodeId());
					return false;
				}
				Folder parentFolder = dataProxy.findFolderById(node.nodeId()).getParentFolder();
				if (ObjectUtils.notEqual(node.parentFolderId(), parentFolder.getId())) {
					log.error(
						"[FOLDER] 올바르지 않은 부모폴더 folderId : {}, currentParentFolderId : {}, correctParentFolderId : {}",
						node.nodeId(), node.parentFolderId(), parentFolder.getId());
					return false;
				}
			// Validate Pick List
			} else if (node.nodeType() == NodeType.PICK) {
				if (!dataProxy.containsPick(node.nodeId())) {
					log.error("[PICK] 존재하지 않는 픽 pickId : {}", node.nodeId());
					return false;
				}
				Folder parentFolder = dataProxy.findPickById(node.nodeId()).getParentFolder();
				if (ObjectUtils.notEqual(node.parentFolderId(), parentFolder.getId())) {
					log.error("[PICK] 올바르지 않은 부모폴더 pickId : {}, currentParentFolderId : {}, correctParentFolderId : {}",
						node.nodeId(), node.parentFolderId(), parentFolder.getId());
					return false;
				}
			} else {
				// Structure에서 한번 검증하지만, 혹시 몰라 한번 더 NodeType검증
				log.error("[NODETYPE] 잘못된 노드타입 : {}", node.nodeType());
				return false;
			}
		}
		return true;
	}
}
