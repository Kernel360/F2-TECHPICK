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

	// NOTE: 쓸것만 검증한다.
	// 근데 어차피 보낼때 이걸 들고 있다가 그대로 데이터 삽입해야 한다.
	// 그럴려면 프론트 전용 node를 메모리에 들고 있어야 하는데 이게 말이 안됨.
	// 전혀 내가 안쓸 값을 들고 있을 이유가 없음.
	// 그런데, 프론트가 보낼 때 id가 없으면, 그건 검증을 백에서 못함. 보내줄때 오류 발생 밖에 안됨.
	// 백엔드는 반드시 유효한 정보만 가지고 있어야 함.
	// 따라서 검증이 필수
	// 즉 잘못 보내진 값은 받아서도 안됨.


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
			String idForFrontend = obj.get("id").getAsString();
			String name = obj.get("name").getAsString();

			Long idForBackend = null;

			// do recur if folder
			if (type == NodeType.FOLDER) {
				// parse folder info
				idForBackend = obj.get("folderId").getAsLong();
				JsonElement children = obj.get("children"); // null if empty children
				if (!children.isJsonNull()) {
					nodes.addAll(flatNodesRecur(children.getAsJsonArray(), idForBackend));
				}
			} else if (type == NodeType.PICK) {
				// parse pick info
				idForBackend = obj.get("pickId").getAsLong();
			} else {
				// 절대 여기에 들어올 수 없습니다.
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
