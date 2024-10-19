package kernel360.techpick.core.model.folder;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
import kernel360.techpick.core.model.common.BaseEntity;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.core.util.OrderConverter;
import kernel360.techpick.feature.domain.folder.exception.ApiFolderException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "folder")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Folder extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "folder_type", nullable = false)
	private FolderType folderType;

	@ManyToOne(fetch = FetchType.LAZY)
	// 부모 폴더가 삭제되면 자식폴더 또한 삭제됨, OnDelete 옵션을 위해 FK필요
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "parent_folder_id")
	private Folder parentFolder;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	// 폴더에 속한 자식 folder id들을 공백으로 분리된 String으로 변환하여 db에 저장
	// ex) [6,3,2,23,1] -> "6 3 2 23 1"
	@Convert(converter = OrderConverter.class)
	@Column(name = "child_folder_order", columnDefinition = "longblob", nullable = false)
	private List<Long> childFolderOrderList;

	// 폴더에 속한 pick id들을 공백으로 분리된 String으로 변환하여 db에 저장
	// ex) [6,3,2,23,1] -> "6 3 2 23 1"
	@Convert(converter = OrderConverter.class)
	@Column(name = "pick_order", columnDefinition = "longblob", nullable = false)
	private List<Long> childPickOrderList;

	public Folder updateChildPickOrder(Long pickId, Integer destination) {
		if (destination == null) {
			return this;
		}
		if (destination < 0 || childPickOrderList.size() < destination) {
			throw ApiFolderException.INVALID_PICK_MOVE_OPERATION();
		}

		List<Long> newOrderList = new ArrayList<>();
		for (int i = 0; i<=childPickOrderList.size(); i++) {
			if (childPickOrderList.get(i).equals(pickId)) {
				continue;
			}
			if (i == destination) {
				newOrderList.add(pickId);
				continue;
			}
			if (i < childPickOrderList.size()) {
				newOrderList.add(childPickOrderList.get(i));
			}
        }
		return this;
	}

	public Folder removeChildPickOrder(Long pickId) {
		this.childPickOrderList.remove(pickId);
		return this;
	}

	private Folder(
		String name,
		FolderType folderType,
		Folder parentFolder,
		User user,
		List<Long> childFolderOrderList,
		List<Long> childPickOrderList
	) {
		this.name = name;
		this.folderType = folderType;
		this.parentFolder = parentFolder;
		this.user = user;
		this.childFolderOrderList = childFolderOrderList;
		this.childPickOrderList = childPickOrderList;
	}
}
