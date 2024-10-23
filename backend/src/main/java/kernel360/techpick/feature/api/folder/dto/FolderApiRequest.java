package kernel360.techpick.feature.api.folder.dto;

public class FolderApiRequest {

	public record Create(
		Long folderId,
		String name,
		Long parentFolderId
	) {
	}

	public record Read(
		Long folderId
	) {
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
