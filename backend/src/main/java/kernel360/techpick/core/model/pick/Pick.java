package kernel360.techpick.core.model.pick;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kernel360.techpick.core.model.common.BaseEntity;
import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.core.model.link.Link;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.core.util.OrderConverter;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pick")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pick extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	// 사용자
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	// 북마크 대상 링크
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "link_id", nullable = false)
	private Link link;

	// 부모 폴더가 삭제되면 자식픽 또한 삭제됨, OnDelete 옵션을 위해 FK필요
	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "parent_folder_id", nullable = false)
	private Folder parentFolder;

	// 사용자가 수정 가능한 Pick 제목. 기본값은 원문 제목과 동일
	@Column(name = "title", nullable = false)
	private String title = "";

	// 픽에 속한 tag id들을 공백으로 분리된 String으로 변환하여 db에 저장. Ex) [6,3,2,23,1] -> "6 3 2 23 1"
	@Convert(converter = OrderConverter.class)
	@Column(name = "tag_order", columnDefinition = "longblob", nullable = false)
	private List<Long> tagOrder = new ArrayList<>();

	// 사용자가 링크에 대해 남기는 메모 (update시 null과 ""를 구분하기 위함)
	@Column(name = "memo", nullable = false)
	private String memo = "";

	@Builder
	private Pick(User user, Link link, Folder parentFolder, String title, List<Long> tagOrder, String memo) {
		this.user = user;
		this.link = link;
		this.parentFolder = parentFolder;
		this.title = title;
		this.tagOrder = tagOrder;
		this.memo = memo;
	}

	public Pick updateTagOrder(List<Long> tagOrder) {
		if (tagOrder == null) return this;
		this.tagOrder = tagOrder;
		return this;
	}

	public Pick updateParentFolder(Folder parentFolder) {
		if (parentFolder == null) return this;
		this.parentFolder = parentFolder;
		return this;
	}

	public Pick updateTitle(String title) {
		if (title == null) return this;
		this.title = title;
		return this;
	}

	public Pick updateMemo(String memo) {
		if (memo == null) return this;
		this.memo = memo;
		return this;
	}
}
