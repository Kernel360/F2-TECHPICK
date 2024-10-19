package kernel360.techpick.feature.infrastructure.link.writer;

import kernel360.techpick.core.model.link.Link;
import kernel360.techpick.core.model.pick.Pick;
import kernel360.techpick.feature.domain.link.dto.LinkInfo;
import kernel360.techpick.feature.domain.link.exception.ApiLinkException;
import kernel360.techpick.feature.domain.pick.exception.ApiPickException;

public interface LinkWriter {

    Link write(LinkInfo linkInfo);
}
