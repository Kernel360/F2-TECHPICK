package kernel360.techpick.feature.domain.pick.dto;

import java.util.List;

import kernel360.techpick.feature.domain.link.dto.LinkInfo;

public record PickResult(
    Long id,
    String title,
    String memo,
    LinkInfo linkInfo,
    List<Long> tagOrder
) {}
