package kernel360.techpick.core.feature.event;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kernel360.techpick.core.common.model.TimeTracking;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "link_view_event")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LinkViewEvent extends TimeTracking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	// 사용자 id
	@Column(name = "user_id", nullable = false)
	private Long userId;

	// 사용자가 담은 링크 id
	@Column(name = "link_id", nullable = false)
	private Long linkId;

	// TODO: 엔티티 사용자가 정적 팩토리 메소드로 필요한 함수를 구현 하세요

	private LinkViewEvent(Long userId, Long linkId) {
		this.userId = userId;
		this.linkId = linkId;
	}
}
