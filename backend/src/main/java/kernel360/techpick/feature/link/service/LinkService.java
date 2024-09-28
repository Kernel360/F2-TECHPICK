package kernel360.techpick.feature.link.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kernel360.techpick.core.exception.feature.link.ApiLinkException;
import kernel360.techpick.core.model.link.Link;
import kernel360.techpick.feature.link.model.dto.LinkRequest;
import kernel360.techpick.feature.link.model.dto.LinkResponse;
import kernel360.techpick.feature.link.model.mapper.LinkMapper;
import kernel360.techpick.feature.link.model.provider.LinkProvider;
import kernel360.techpick.feature.pick.repository.PickRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LinkService {

	private final LinkMapper linkMapper;
	private final LinkProvider linkProvider;
	private final PickRepository pickRepository;

	/**
	 * 주어진 URL이 데이터베이스에 존재하지 않는 경우 새로운 {@code Link}를 생성
	 *
	 * @param request {@code LinkRequest}
	 * @throws ApiLinkException [LI-002] 이미 존재하는 링크(URL)
	 */
	@Transactional
	public LinkResponse createLink(LinkRequest request) throws ApiLinkException {

		Link link = linkMapper.createLink(request);
		validateUrl(request.url());

		return linkMapper.createLinkResponse(linkProvider.save(link));
	}

	@Transactional(readOnly = true)
	public LinkResponse getLinkById(Long id) throws ApiLinkException {

		Link link = linkProvider.findById(id);

		return linkMapper.createLinkResponse(link);
	}

	@Transactional(readOnly = true)
	public LinkResponse getLinkByUrl(String url) throws ApiLinkException {

		Link link = linkProvider.findByUrl(url);

		return linkMapper.createLinkResponse(link);
	}

	@Transactional(readOnly = true)
	public List<LinkResponse> getLinkAll() {

		List<Link> linkList = linkProvider.findAll();

		return linkList.stream()
			.map(linkMapper::createLinkResponse)
			.toList();
	}

	@Transactional
	public void deleteLinkById(Long id) throws ApiLinkException {

		Link targetLink = linkProvider.findById(id);
		validateLinkHasPick(targetLink);
		linkProvider.deleteById(id);
	}

	private void validateUrl(String url) throws ApiLinkException {

		if (linkProvider.existsByUrl(url)) {
			throw ApiLinkException.LINK_ALREADY_EXISTS();
		}
	}

	private void validateLinkHasPick(Link link) throws ApiLinkException {

		if (pickRepository.existsByLink(link)) {
			throw ApiLinkException.LINK_HAS_PICKS();
		}
	}
}
