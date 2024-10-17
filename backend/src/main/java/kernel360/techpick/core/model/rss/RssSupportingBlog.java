package kernel360.techpick.core.model.rss;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kernel360.techpick.core.model.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "rss_blog")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RssSupportingBlog extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	// Rss 피드 주소
	@Column(name = "rss_feed_url", nullable = false, unique = true)
	private String rssFeedUrl;

	// TODO: 엔티티 사용자가 정적 팩토리 메소드로 필요한 함수를 구현 하세요
	private RssSupportingBlog(String rssFeedUrl) {
		this.rssFeedUrl = rssFeedUrl;
	}

	public static RssSupportingBlog create(String rssFeedUrl) {
		return new RssSupportingBlog(rssFeedUrl);
	}
}
