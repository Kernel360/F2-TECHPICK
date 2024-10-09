package kernel360.techpick.feature.link.model;

import kernel360.techpick.core.model.link.Link;
import kernel360.techpick.feature.link.service.dto.LinkRequest;
import kernel360.techpick.feature.link.service.dto.LinkResponse;

public class LinkMapper {

	public static Link toLinkEntity(LinkRequest linkRequest) {
		return Link.create(linkRequest.title(), linkRequest.description(), linkRequest.url());
	}

	public static LinkResponse toLinkResponse(Link link) {
		return new LinkResponse(
			link.getId(),
			link.getUrl(),
			link.getTitle(),
			link.getDescription(),
			link.getImageUrl()
		);
	}
}
