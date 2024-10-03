package kernel360.techpick.feature.link.model;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.link.Link;
import kernel360.techpick.feature.link.service.dto.LinkRequest;
import kernel360.techpick.feature.link.service.dto.LinkResponse;
import kernel360.techpick.feature.pick.service.dto.PickCreateRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LinkMapper {

	public Link createLink(LinkRequest linkRequest) {
		return Link.create(linkRequest.title(), linkRequest.description(), linkRequest.url());
	}

	public Link createLink(PickCreateRequest pickCreateRequest) {
		return Link.create(pickCreateRequest.linkRequest().url(), pickCreateRequest.linkRequest().title(),
			pickCreateRequest.linkRequest().description(),
			pickCreateRequest.linkRequest().imageUrl());
	}

	public LinkResponse createLinkResponse(Link link) {
		return new LinkResponse(
			link.getId(),
			link.getUrl(),
			link.getTitle(),
			link.getDescription(),
			link.getImageUrl()
		);
	}
}
