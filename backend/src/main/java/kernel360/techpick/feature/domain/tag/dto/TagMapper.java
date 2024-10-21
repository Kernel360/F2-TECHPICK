package kernel360.techpick.feature.domain.tag.dto;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import kernel360.techpick.core.model.tag.Tag;
import kernel360.techpick.core.model.user.User;

@Mapper(
	componentModel = "spring",
	injectionStrategy = InjectionStrategy.CONSTRUCTOR,
	unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface TagMapper {

	TagResult.Read toRead(Tag tag);

	TagResult.Create toCreate(Tag tag);

	TagResult.Update toUpdate(Tag tag);

	Tag createToEntity(TagCommand.Create create, User user);
}
