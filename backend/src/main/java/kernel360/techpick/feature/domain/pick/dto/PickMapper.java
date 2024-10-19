package kernel360.techpick.feature.domain.pick.dto;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.core.model.link.Link;
import kernel360.techpick.core.model.pick.Pick;
import kernel360.techpick.core.model.user.User;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface PickMapper {

    PickResult.Create toCreateResult(Pick pick);

    PickResult.Read toReadResult(Pick pick);

    PickResult.Update toUpdateResult(Pick pick);

    PickResult.Move toMoveResult(Pick pick);

    Pick toEntity(PickCommand.Create command, User user, Folder parentFolder, Link link);
}
