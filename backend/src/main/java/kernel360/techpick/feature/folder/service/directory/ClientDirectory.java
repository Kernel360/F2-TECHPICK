package kernel360.techpick.feature.folder.service.directory;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotNull;
import kernel360.techpick.feature.folder.exception.ApiFolderException;
import kernel360.techpick.feature.folder.service.directory.internal.ClientNode;
import kernel360.techpick.feature.folder.service.directory.internal.PickNode;
import kernel360.techpick.feature.folder.service.directory.internal.RelationalNode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientDirectory {

	private static final ObjectMapper mapper = new ObjectMapper();
	private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@NotNull
	@JsonProperty("root")
	private List<ClientNode> rootFolder;

	@NotNull
	@JsonProperty("recycleBin")
	private List<ClientNode> recycleBinFolder;

	@NotNull
	@JsonProperty("unclassified")
	private List<PickNode> unclassifiedFolder;

	public List<ClientNode> getRootFolder() {
		return rootFolder;
	}

	public List<ClientNode> getRecycleBinFolder() {
		return recycleBinFolder;
	}

	public List<PickNode> getUnclassifiedFolder() {
		return unclassifiedFolder;
	}

	public static ClientDirectory fromJson(String json) {
		try {
			ClientDirectory structure = mapper.readerFor(ClientDirectory.class).readValue(json);
			validateDirectoryConstraint(structure);
			return structure;
		} catch (Exception e) {
			log.warn("Json Input : {}", json);
			log.warn("Error Log :", e);
			throw ApiFolderException.FOLDER_INVALID_JSON_STRUCTURE();
		}
	}

	/**
	 * 현재는 Root 폴더만 변환하므로 명시적으로 함수를 하나 만듬.
	 */
	public List<RelationalNode> convertRootToNodeList(Long rootFolderId) {
		return rootFolder.stream()
						 .flatMap(child -> child.toNodeList(rootFolderId).stream())
						 .toList();
	}

	/**
	 * json 필드가 누락됬거나, null이거나, 빈 문자열(ex. name)일 경우 예외 발생
	 */
	private static void validateDirectoryConstraint(ClientDirectory structure) {
		Set<ConstraintViolation<ClientDirectory>> violations = validator.validate(structure);
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
