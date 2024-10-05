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

	public static @Valid ClientDirectory fromJson(String json) {
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

	public List<RelationalNode> toRelationalNodeList(Long rootFolderId) {
		return rootFolder.stream()
						 .flatMap(child -> child.flatNode(rootFolderId).stream())
						 .toList();
	}

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
