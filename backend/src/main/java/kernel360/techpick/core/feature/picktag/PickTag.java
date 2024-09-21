package kernel360.techpick.core.feature.picktag;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kernel360.techpick.core.common.model.CreatedAndUpdatedTimeColumn;
import kernel360.techpick.core.feature.tag.Tag;
import kernel360.techpick.core.feature.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pick_tag")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PickTag extends CreatedAndUpdatedTimeColumn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pick_tag_id")
	private Long pickTagId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tag_id")
	private Tag tag;

	public PickTag(User user, Tag tag) {
		this.user = user;
		this.tag = tag;
	}
}
