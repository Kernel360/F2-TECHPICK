package kernel360.techpick.feature.domain.pick.dto;

import java.util.List;

import kernel360.techpick.feature.domain.link.dto.LinkInfo;

public class PickCommand {

    public record Read(Long pickId) {}

    public record Create(String title, String memo, List<Long> tagOrder, Long parentFolderId, LinkInfo linkInfo) {}

    public record Update(Long pickId, String title, String memo, List<Long> tagIdList) {}

    public record Move(Long pickId, Long parentFolderId, int orderIdx) {}

    public record Delete(Long pickId) {}
}
