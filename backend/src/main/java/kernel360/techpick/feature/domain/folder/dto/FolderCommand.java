package kernel360.techpick.feature.domain.folder.dto;

public class FolderCommand {

	public record Create(
		Long userId,
		String name,
		Long parentFolderId) {
	}

	public record Read(
		Long userId,
		Long folderId) {
	}

	public record Update(
		Long userId,
		Long folderId,
		String name
	) {
	}

	public record Move(
		Long userId,
		Long folderId,
		Long parentFolderId,
		int orderIdx
	) {
	}

	public record Delete(
		Long userId,
		Long folderId
	) {
	}
}
