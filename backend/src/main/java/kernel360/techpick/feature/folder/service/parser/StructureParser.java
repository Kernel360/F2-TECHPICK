package kernel360.techpick.feature.folder.service.parser;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import kernel360.techpick.feature.folder.exception.ApiFolderException;
import kernel360.techpick.feature.folder.service.parser.internal.IllegalNodeTypeException;
import kernel360.techpick.feature.folder.service.parser.internal.NodeType;
import kernel360.techpick.feature.folder.service.parser.internal.StructureNode;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class StructureParser {

	// TODO: 1. Gson 말고 Spring이 기본 제공하는 Jackson 으로 변경.
	//       2. 나중에 코드 통으로 날릴 거라, 재귀 로직은 그대로 두겠음.
	private static final Long PARENT_OF_ROOT = null;
	private final Gson parser = new Gson();

	private List<StructureNode> flatNodesRecur(
		JsonArray array,
		Long parentFolderId
	) throws IllegalNodeTypeException {

		List<StructureNode> nodes = new ArrayList<>();
		if (array.isJsonNull()) {
			return nodes;
		}

		for (JsonElement element : array) {
			if (element.isJsonNull()) {
				continue;
			}
			// parse common info
			JsonObject obj = element.getAsJsonObject();
			NodeType type = NodeType.from(obj.get("type").getAsString());
			String clientNodeId = obj.get("id").getAsString(); // 미사용 값이지만 타입 검증 진행
			String name = obj.get("name").getAsString(); // 미사용 값이지만 타입 검증 진행
			Long serverNodeId = null;

			// parse by each type
			if (type == NodeType.FOLDER) {
				serverNodeId = obj.get("folderId").getAsLong();
				JsonElement children = obj.get("children"); // null if empty children
				if (!children.isJsonNull()) {
					nodes.addAll(flatNodesRecur(children.getAsJsonArray(), serverNodeId));
				}
			} else if (type == NodeType.PICK) {
				serverNodeId = obj.get("pickId").getAsLong();
			}

			nodes.add(
				StructureNode.builder().nodeId(serverNodeId)
									   .parentFolderId(parentFolderId)
									   .nodeType(type)
									   .build()
			);
		}

		return nodes;
	}

	/**
	 * @implNote 나중에 RDB로 폴더 책임이 넘어가면, 전부 삭제될 함수입니다....!
	 * @param json 직렬화된 json 문자열
	 * @throws ApiFolderException
	 *         - 유효하지 않는 json 포맷일 경우 예외 발생
	 *         - 서비스에서 정의한 json 포맷과 다를 경우 예외 발생
	 */
	public List<StructureNode> parseFolderStructure(String json) {

		try {
			JsonArray array = parser.fromJson(json, JsonArray.class);
			return flatNodesRecur(array, PARENT_OF_ROOT);
		} catch (Exception e) {
			log.warn("Json Input : {}", json);
			log.warn("Error Log :", e);
			throw ApiFolderException.FOLDER_INVALID_JSON_STRUCTURE();
		}
	}
}
