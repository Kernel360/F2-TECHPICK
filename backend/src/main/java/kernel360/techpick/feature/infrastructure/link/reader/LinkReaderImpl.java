package kernel360.techpick.feature.infrastructure.link.reader;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.link.Link;
import kernel360.techpick.core.model.link.LinkRepository;
import kernel360.techpick.feature.domain.link.exception.ApiLinkException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LinkReaderImpl implements LinkReader {
    private final LinkRepository linkRepository;

    @Override
    public Link read(String url) {
        return linkRepository.findByUrl(url).orElseThrow(ApiLinkException::LINK_NOT_FOUND);
    }
}
