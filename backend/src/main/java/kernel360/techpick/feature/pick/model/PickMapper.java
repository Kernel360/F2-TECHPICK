package kernel360.techpick.feature.pick.model;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.core.model.link.Link;
import kernel360.techpick.core.model.pick.Pick;
import kernel360.techpick.core.model.pick.PickTag;
import kernel360.techpick.core.model.tag.Tag;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.link.model.LinkMapper;
import kernel360.techpick.feature.link.service.dto.LinkUrlResponse;
import kernel360.techpick.feature.pick.service.dto.PickCreateRequest;
import kernel360.techpick.feature.pick.service.dto.PickResponse;
import kernel360.techpick.feature.tag.model.TagMapper;
import kernel360.techpick.feature.tag.model.TagProvider;
import kernel360.techpick.feature.tag.service.dto.TagResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PickMapper {

	public Pick toPickEntity(User user, Link link, Folder folder, PickCreateRequest request) {
		return Pick.create(user, link, folder, request.title(), request.memo());
	}

	public PickResponse toPickResponse(Pick pick, List<TagResponse> tagResponseList, LinkUrlResponse linkUrlResponse) {
		return new PickResponse(pick.getId(), pick.getCustomTitle(), pick.getMemo(), pick.getParentFolder().getId(),
			pick.getUser().getId(), tagResponseList, linkUrlResponse);
	}

	public List<PickResponse> toPickResponseList(List<Pick> pickList, PickTagProvider pickTagProvider,
		TagMapper tagMapper, LinkMapper linkMapper) {
		return pickList.stream().map(pick -> {
			List<PickTag> pickTagList = pickTagProvider.findAllPickTagByPickId(pick.getId());
			List<TagResponse> tagResponseList = tagMapper.toTagResponse(pickTagList);
			LinkUrlResponse linkUrlResponse = linkMapper.toLinkUrlResponse(pick.getLink());

			return this.toPickResponse(pick, tagResponseList, linkUrlResponse);
		}).toList();
	}

}
