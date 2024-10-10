package kernel360.techpick.feature.structure.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotNull;
import kernel360.techpick.feature.structure.exception.ApiStructureException;
import kernel360.techpick.feature.structure.service.node.common.Node;
import kernel360.techpick.feature.structure.service.node.server.RelationalNode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class Structure<T extends Node> {

	private static final ObjectMapper mapper = new ObjectMapper();
	private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@NotNull
	@JsonProperty("root")
	private List<T> rootFolder;

	@NotNull
	@JsonProperty("recycleBin")
	private List<T> recycleBinFolder;

	public Structure() {
		this.rootFolder = new ArrayList<>();
		this.recycleBinFolder = new ArrayList<>();
	}

	public Structure(List<T> rootFolder, List<T> recycleBinFolder) {
		this.rootFolder = rootFolder;
		this.recycleBinFolder = recycleBinFolder;
	}

	public String serialize() {
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			throw ApiStructureException.INVALID_JSON_STRUCTURE(); // TODO: change to structure exception
		}
	}

	/**
	 * NOTE: 서버에서 구조 트리 검증을 하게 될 경우,
	 *       이 함수로 parent_id가 세팅된 리스트를 획득할 것.
	 */
	public List<RelationalNode> convertRootToNodeList(Long rootFolderId) {
		return rootFolder.stream()
			.flatMap(child -> child.toNodeList(rootFolderId).stream())
			.toList();
	}

	/**
	 * NOTE: 서버에서 구조 트리 검증을 하게 될 경우,
	 *       이 함수로 parent_id가 세팅된 리스트를 획득할 것.
	 */
	public List<RelationalNode> convertRecycleBinToNodeList(Long recycleBinFolderId) {
		return recycleBinFolder.stream()
			.flatMap(child -> child.toNodeList(recycleBinFolderId).stream())
			.toList();
	}

	/**
	 * json 데이터를 비직렬화 합니다.
	 * 어떤 노드 포맷 (클라이언트용 or 서버용) 으로 맞출 것인지 설정할 수 있습니다.
	 * 구체적인 사용 방법은 테스트 코드 참고해주세요.
	 */
	public static <T extends Node> Structure<T> fromJson(String json, Class<T> node) {
		try {
			JavaType type = mapper.getTypeFactory().constructParametricType(Structure.class, node);
			Structure<T> structure = mapper.readValue(json, type);
			validateDirectoryConstraint(structure);
			return structure;
		} catch (Exception e) {
			log.warn("Json Input : {}", json);
			log.warn("Error Log :", e);
			throw ApiStructureException.INVALID_JSON_STRUCTURE();
		}
	}

	/**
	 * json 필드가 누락됬거나, null이거나, 빈 문자열(ex. name)일 경우 예외 발생
	 */
	private static <T extends Node> void validateDirectoryConstraint(Structure<T> structure) {
		Set<ConstraintViolation<Structure<T>>> violations = validator.validate(structure);
		if (!violations.isEmpty()) {
			throw new ValidationException(
				String.format(
					"Validation failed for object of type '%s': %s",
					structure.getClass().getName(), violations
				)
			);
		}
	}
}
