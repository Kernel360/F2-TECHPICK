package kernel360.techpick.feature.api.folder.dto;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import kernel360.techpick.feature.domain.folder.dto.FolderCommand;
import kernel360.techpick.feature.domain.folder.dto.FolderResult;

@Mapper(
	componentModel = "spring",
	injectionStrategy = InjectionStrategy.CONSTRUCTOR,
	unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface FolderApiMapper {
	FolderCommand.Create toCreateCommand(FolderApiRequest.Create request, Long userId);

	FolderCommand.Read toReadCommand(FolderApiRequest.Read request, Long userId);

	FolderCommand.Update toUpdateCommand(FolderApiRequest.Update request, Long userId);

	FolderCommand.Move toMoveCommand(FolderApiRequest.Move request, Long userId);

	FolderCommand.Delete toDeleteCommand(FolderApiRequest.Delete request, Long userId);

	FolderApiResponse.Create toCreateResponse(FolderResult request, Long userId);

	FolderApiResponse.Read toReadResponse(FolderResult request, Long userId);
}
