package kernel360.techpick.feature.link.model.dto;

import kernel360.techpick.core.model.link.Link;

public record LinkResponse(Long id, String url, String title, String description) {
	public static LinkResponse of(Link link) {
		return new LinkResponse(
			link.getId(),
			link.getUrl(),
			link.getTitle(),
			link.getDescription()
		);
	}
}
