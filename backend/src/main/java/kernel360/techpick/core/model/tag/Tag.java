package kernel360.techpick.core.model.tag;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kernel360.techpick.core.model.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "tag")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {

	/**
	 * TODO: 태그 기반 전체 사용자 Pick 검색 기능
	 *       - 이를 위해선 사용자 마다 다른 태그들을 정규화해야 하는데, 이는 나중에 고민해보기
	 *       - Ex. "Ci/CD", "CI CD" --> 공백, 특수문자 제거 --> "cicd"
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	// 태그명
	@Column(name = "name", nullable = false)
	private String name;

	// order로 하니까 db 키워드랑 겹쳐 안됨..
	@Column(name = "tag_order", nullable = false)
	private int tagOrder;

	// 사용자 FK
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	// TODO: 엔티티 사용자가 정적 팩토리 메소드로 필요한 함수를 구현 하세요
	public static Tag createTag(String name, int order, User user) {
		return new Tag(name, order, user);
	}

	public void updateTag(String name, int tagOrder) {
		this.name = name;
		this.tagOrder = tagOrder;
	}

	private Tag(String name, int tagOrder, User user) {
		this.name = name;
		this.tagOrder = tagOrder;
		this.user = user;
	}
}
