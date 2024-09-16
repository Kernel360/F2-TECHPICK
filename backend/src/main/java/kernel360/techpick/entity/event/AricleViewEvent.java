package kernel360.techpick.entity.event;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kernel360.techpick.entity.article.Article;
import kernel360.techpick.entity.common.CreatedAndUpdatedTimeColumn;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

// NOTE: 사용자 조회 추적 모델
// TODO: 1분 동안 반복 되는 조회는 1번으로 칠 것.
@Table(name = "article_view_event")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AricleViewEvent extends CreatedAndUpdatedTimeColumn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "article_view_event_id", updatable = false)
	private Long articleViewEventId;

	// 이메일
	@Column(name = "email", nullable = false)
	private String email;

	// 이벤트 발생 위치
	@Enumerated(EnumType.STRING)
	@Column(name = "event_location", nullable = false)
	private EventLocation eventLocation;

	// 조회한 게시글
	@ManyToOne
	@JoinColumn(name = "article_id", nullable = false)
	private Article article;
}
