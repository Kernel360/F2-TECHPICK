package kernel360.techpick.pick.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kernel360.techpick.common.model.entity.CreatedAndUpdatedTimeColumn;
import kernel360.techpick.link.model.entity.Link;
import kernel360.techpick.user.model.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pick")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pick extends CreatedAndUpdatedTimeColumn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pickId;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "memo", nullable = false)
	private String memo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "link_id")
	private Link link;

	public Pick(String title, String memo, User user, Link link) {
		this.title = title;
		this.memo = memo;
		this.user = user;
		this.link = link;
	}
}
