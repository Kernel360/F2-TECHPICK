package kernel360.techpick.core.model.folder;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kernel360.techpick.core.model.common.TimeTracking;
import kernel360.techpick.core.model.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "folder")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Folder extends TimeTracking {

	private static final Long NO_PARENT_FOLDER = -1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "folder_type", nullable = false)
	private FolderType folderType;

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "parent_folder_id")
	private Folder parentFolder;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	private Folder(String name, FolderType folderType, Folder parentFolder, User user) {
		this.name = name;
		this.folderType = folderType;
		this.parentFolder = parentFolder;
		this.user = user;
	}

	public void updateName(String name) {
		this.name = name;
	}

	public void updateParentFolder(Folder parentFolder) {
		this.parentFolder = parentFolder;
	}

	public Long findParentFolderId() {
		if (parentFolder == null) {
			return NO_PARENT_FOLDER;
		}
		return parentFolder.getId();
	}

	// TODO: 엔티티 사용자가 정적 팩토리 메소드로 필요한 함수를 구현 하세요
	public static Folder create(String name, FolderType folderType, User user) {
		return new Folder(name, folderType, null, user);
	}
}
