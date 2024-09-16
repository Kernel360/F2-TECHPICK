package kernel360.techpick.entity.article;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import kernel360.techpick.entity.blog.Blog;
import kernel360.techpick.entity.common.CreatedAndUpdatedTimeColumn;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "article")
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends CreatedAndUpdatedTimeColumn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "article_id")
	private Long articleId;

	// 원문 제목
	@Column(name = "title", nullable = false)
	private String title;

	// 원문 링크
	@Column(name = "url", nullable = false)
	private String url;

	// 원문 작성 일자
	@Column(name = "published_at", nullable = false)
	private LocalDateTime publishedAt;

	// 원문 작성자
	@Column(name = "author") // nullable (수집 안될 수도 있음)
	private String author;

	// 원문 출처 블로그
	@ManyToOne
	@JoinColumn(name = "blog_id", nullable = false)
	private Blog blog;

	// 수집 날짜 (크롤링 시각)
	@Column(name = "crawled_at", nullable = false)
	private LocalDateTime crawled_at;

	// 대표 이미지 CDN url
	@Column(name = "image_url")
	private String imageUrl; // nullable

	// [사용자 - 관심 토픽] 1:N 테이블
	@OneToMany(mappedBy = "article")
	private List<ArticleTopic> articleTopics = new ArrayList<>();

	// 게시글-토픽 관계 테이블
	// @ManyToMany
	// @JoinTable(
	// 	name = "article_topic",
	// 	joinColumns = @JoinColumn(name = "article_id", nullable = false),
	// 	inverseJoinColumns = @JoinColumn(name = "topic_id", nullable = false)
	// )
	// private List<Topic> topics = new ArrayList<>();
}
