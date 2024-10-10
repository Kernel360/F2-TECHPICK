package kernel360.techpick.feature.link.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kernel360.techpick.feature.link.exception.ApiLinkException;
import kernel360.techpick.core.model.link.Link;
import kernel360.techpick.feature.link.service.dto.LinkResponse;
import kernel360.techpick.feature.link.model.LinkMapper;
import kernel360.techpick.feature.link.model.LinkProvider;
import kernel360.techpick.feature.pick.repository.PickRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LinkService {

	private final LinkProvider linkProvider;
	private final LinkMapper linkMapper;
	private final PickRepository pickRepository;

	@Transactional(readOnly = true)
	public LinkResponse getLinkById(Long id) throws ApiLinkException {

		return linkMapper.toLinkResponse(linkProvider.findById(id));
	}

	@Transactional(readOnly = true)
	public LinkResponse getLinkByUrl(String url) throws ApiLinkException {

		return linkMapper.toLinkResponse(linkProvider.findByUrl(url));
	}

	@Transactional(readOnly = true)
	public List<LinkResponse> getLinkAll() {

		return linkProvider.findAll().stream()
			.map(linkMapper::toLinkResponse)
			.toList();
	}

	@Transactional
	public void deleteLinkById(Long id) throws ApiLinkException {

		validateLinkHasPick(linkProvider.findById(id));
		linkProvider.deleteById(id);
	}

	private void validateLinkHasPick(Link link) throws ApiLinkException {

		if (pickRepository.existsByLink(link)) {
			throw ApiLinkException.LINK_HAS_PICKS();
		}
	}
}
