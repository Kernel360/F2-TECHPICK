package kernel360.techpick.feature.domain.pick.dto;

public class PickResult {

    public record Read(String title, String memo, String url) {}

    public record Create(String title, String memo, String url) {}

    public record Update(String title, String memo, String url) {}

    public record Move(String title, String memo, String url) {}
}
