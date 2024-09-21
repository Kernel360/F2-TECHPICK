package kernel360.techpick.core.feature.event;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kernel360.techpick.core.common.model.CreatedAndUpdatedTimeColumn;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "link_view_event")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LinkViewEvent extends CreatedAndUpdatedTimeColumn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "link_view_event_id")
	private Long linkViewEventId;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "link_id", nullable = false)
	private Long linkId;

	public LinkViewEvent(Long userId, Long linkId) {
		this.userId = userId;
		this.linkId = linkId;
	}
}
