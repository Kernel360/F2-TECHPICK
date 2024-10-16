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
import jakarta.persistence.UniqueConstraint;
import kernel360.techpick.core.model.common.TimeTracking;
import kernel360.techpick.core.model.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(
	name = "folder",
	uniqueConstraints = {
		@UniqueConstraint(
			name = "UC_FOLDER_NAME_PER_USER",
			columnNames = {"user_id, parent_folder_id, name"}
		)
	}
)
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Folder extends TimeTracking {

	private static final Long NO_PARENT_FOLDER = -1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	// NOTE: 10.15 김민규 - 한글 이름이 0.01초 차이로 2번 요청와서, 긴급하게 막고자 추가합니다.
	@Column(name = "name", nullable = false, unique = true)
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
	public static Folder generalFolder(String name, User user) {
		return new Folder(name, FolderType.GENERAL, null, user);
	}

	public static Folder rootFolder(String name, User user) {
		return new Folder(name, FolderType.ROOT, null, user);
	}

	public static Folder recycleBinFolder(String name, User user) {
		return new Folder(name, FolderType.RECYCLE_BIN, null, user);
	}

	public static Folder unclassifedFolder(String name, User user) {
		return new Folder(name, FolderType.UNCLASSIFIED, null, user);
	}
}
