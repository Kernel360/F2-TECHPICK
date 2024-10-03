package kernel360.techpick.feature.folder.service.parser.internal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.parameters.P;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import kernel360.techpick.feature.folder.exception.ApiFolderException;
import kernel360.techpick.feature.folder.service.parser.StructureParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StructureParserImpl implements StructureParser {

	private static final Long PARENT_OF_ROOT = null;
	private final Gson parser = new Gson();

	private List<StructureNode> flatNodesRecur(JsonArray array, Long parentFolderId) {

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

			NodeType type = NodeType.valueOfLabel(obj.get("type").getAsString());
			String idForFrontend = obj.get("id").getAsString(); // 미사용 값이지만 타입 검증 진행
			String name = obj.get("name").getAsString(); // 미사용 값이지만 타입 검증 진행
			Long idForBackend = null;

			// parse by each type
			if (type == NodeType.FOLDER) {
				idForBackend = obj.get("folderId").getAsLong();
				JsonElement children = obj.get("children"); // null if empty children
				if (!children.isJsonNull()) {
					nodes.addAll(flatNodesRecur(children.getAsJsonArray(), idForBackend));
				}
			} else if (type == NodeType.PICK) {
				idForBackend = obj.get("pickId").getAsLong();
			}

			nodes.add(
				StructureNode.builder().nodeId(idForBackend)
									   .parentFolderId(parentFolderId)
									   .nodeType(type)
									   .build()
			);
		}

		return nodes;
	}

	@Override
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
