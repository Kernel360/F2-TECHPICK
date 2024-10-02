package kernel360.techpick.core.model.folder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import kernel360.techpick.core.model.common.TimeTracking;
import kernel360.techpick.core.model.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "folder_structure")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FolderStructure extends TimeTracking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "longblob", nullable = false)
	private String structure;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	private FolderStructure(String structure, User user) {
		this.structure = structure;
		this.user = user;
	}

	public void updateStructure(String structure) {
		this.structure = structure;
	}

	// TODO: 엔티티 사용자가 정적 팩토리 메소드로 필요한 함수를 구현 하세요
	public static FolderStructure create(String structure, User user) {
		return new FolderStructure(structure, user);
	}
}
