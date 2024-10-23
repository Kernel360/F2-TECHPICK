package kernel360.techpick.feature.api.tag.dto;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import kernel360.techpick.feature.domain.tag.dto.TagCommand;
import kernel360.techpick.feature.domain.tag.dto.TagResult;

@Mapper(
	componentModel = "spring",
	injectionStrategy = InjectionStrategy.CONSTRUCTOR,
	unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface TagApiMapper {

	TagCommand.Read toReadCommand(TagApiRequest.Read request, Long userId);

	TagCommand.Create toCreateCommand(TagApiRequest.Create request, Long userId);

	TagCommand.Update toUpdateCommand(TagApiRequest.Update request, Long userId);

	TagCommand.Delete toDeleteCommand(TagApiRequest.Delete request, Long userId);

	TagApiResponse.Read toReadResponse(TagResult result);

	TagApiResponse.Create toCreateResponse(TagResult result);

	TagApiResponse.Update toUpdateResponse(TagResult result);
}
