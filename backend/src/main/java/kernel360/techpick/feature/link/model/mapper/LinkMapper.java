package kernel360.techpick.feature.link.model.mapper;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.link.Link;
import kernel360.techpick.feature.link.model.dto.LinkRequest;
import kernel360.techpick.feature.link.model.dto.LinkResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LinkMapper {

	public Link createLink(LinkRequest linkRequest) {
		return Link.create(linkRequest.title(), linkRequest.description(), linkRequest.url());
	}

	public LinkResponse createLinkResponse(Link link) {
		return new LinkResponse(
			link.getId(),
			link.getUrl(),
			link.getTitle(),
			link.getDescription()
		);
	}
}
