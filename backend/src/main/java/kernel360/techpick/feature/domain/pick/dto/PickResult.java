package kernel360.techpick.feature.domain.pick.dto;

import java.util.List;

import kernel360.techpick.feature.domain.link.dto.LinkInfo;

public class PickResult {

    public record Read(String title, String memo, LinkInfo linkInfo, List<Long> tagOrder) {}

    public record Create(Long id) {}

    public record Update() {}

    public record Move() {}
}
