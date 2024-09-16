package kernel360.techpick.entity.blog;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


// NOTE: 관리자만 수정 가능한 테이블 입니다.
// TODO: rss를 지원하지 않는 경우 어떻게 처리할지 고민 필요.
@Table(name = "blog")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Blog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "blog_id", updatable = false)
	private Long blogId;

	// 블로그명
	@Column(name = "blog_name", nullable = false)
	private String blogName;

	// 블로그 주소
	@Column(name = "blog_url", nullable = false)
	private String blogUrl;

	// RSS service url
	@Column(name = "rss_url")
	private String rssUrl; // nullable

	// 블로그 기본 대표 이미지
	@Column(name = "default_image", nullable = false)
	private String base64Image;
}
