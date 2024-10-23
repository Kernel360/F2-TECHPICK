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
	FolderCommand.Create toCreateCommand(FolderApiRequest.Create request);

	FolderCommand.Read toReadCommand(FolderApiRequest.Read request);

	FolderCommand.Update toUpdateCommand(FolderApiRequest.Update request);

	FolderCommand.Move toMoveCommand(FolderApiRequest.Move request);

	FolderCommand.Delete toDeleteCommand(FolderApiRequest.Delete request);

	FolderApiResponse.Create toCreateResponse(FolderResult request);

	FolderApiResponse.Read toReadResponse(FolderResult request);
}
