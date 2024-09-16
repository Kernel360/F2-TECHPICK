package kernel360.techpick.entity.subscriber;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kernel360.techpick.entity.common.CreatedAndUpdatedTimeColumn;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "email_subscriber")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailSubscriber extends CreatedAndUpdatedTimeColumn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "email_subscriber_id", updatable = false)
	private Long emailSubscriberId;

	// 구독자 이메일
	@Column(name = "email", nullable = false)
	private String email;
}
