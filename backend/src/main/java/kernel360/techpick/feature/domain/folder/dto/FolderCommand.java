package kernel360.techpick.feature.domain.folder.dto;

public class FolderCommand {

	public record Create(
		String name,
		Long parentFolderId) {
	}

	public record Read(
		Long folderId) {
	}

	public record Update(
		Long folderId,
		String name
	) {
	}

	public record Move(
		Long folderId,
		Long parentFolderId,
		int orderIdx
	) {
	}

	public record Delete(
		Long folderId
	) {
	}
}
