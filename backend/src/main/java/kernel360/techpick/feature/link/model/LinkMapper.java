package kernel360.techpick.feature.link.model;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.link.Link;
import kernel360.techpick.feature.link.service.dto.LinkRequest;
import kernel360.techpick.feature.link.service.dto.LinkResponse;

@Component
public class LinkMapper {

	public Link toLinkEntity(LinkRequest linkRequest) {
		return Link.create(linkRequest.title(), linkRequest.description(), linkRequest.url());
	}

	public LinkResponse toLinkResponse(Link link) {
		return new LinkResponse(
			link.getId(),
			link.getUrl(),
			link.getTitle(),
			link.getDescription(),
			link.getImageUrl()
		);
	}
}
