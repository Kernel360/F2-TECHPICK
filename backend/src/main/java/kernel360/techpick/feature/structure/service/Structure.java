package kernel360.techpick.feature.structure.service;

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
import kernel360.techpick.feature.structure.exception.ApiDirectoryException;
import kernel360.techpick.feature.structure.service.node.common.Node;
import kernel360.techpick.feature.structure.service.node.server.RelationalNode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
@NoArgsConstructor
public class Structure<T extends Node> {

	private static final ObjectMapper mapper = new ObjectMapper();
	private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@NotNull
	@JsonProperty("root")
	private List<T> rootFolder;

	@NotNull
	@JsonProperty("recycleBin")
	private List<T> recycleBinFolder;

	public Structure(List<T> rootFolder, List<T> recycleBinFolder) {
		this.rootFolder = rootFolder;
		this.recycleBinFolder = recycleBinFolder;
	}

	/**
	 * 컨트롤러에서 Structure 객체를 그냥 내보내도 직렬화 됩니다.
	 * 별도로 serialize 함수를 만든 이유는 - DB에 저장하기 전에 직접 직렬화하기 위해서 입니다.
	 */
	public String serialize() {
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			throw ApiDirectoryException.DIRECTORY_INVALID_JSON_STRUCTURE(); // TODO: change to structure exception
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

	public static <T extends Node> Structure<T> fromJson(String json, Class<T> node) {
		try {
			JavaType type = mapper.getTypeFactory().constructParametricType(Structure.class, node);
			Structure<T> structure = mapper.readValue(json, type);
			validateDirectoryConstraint(structure);
			return structure;
		} catch (Exception e) {
			log.warn("Json Input : {}", json);
			log.warn("Error Log :", e);
			throw ApiDirectoryException.DIRECTORY_INVALID_JSON_STRUCTURE();
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
