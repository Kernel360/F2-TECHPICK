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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import kernel360.techpick.entity.blog.Blog;
import kernel360.techpick.entity.common.CreatedAndUpdatedTimeColumn;
import kernel360.techpick.entity.admin.Topic;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "article")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends CreatedAndUpdatedTimeColumn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "article_id", updatable = false)
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
	@Column(name = "author", nullable = false)
	private LocalDateTime author;

	// 원문 출처 블로그
	@ManyToOne
	@JoinColumn(name = "blog_id", nullable = false)
	private Blog blog;

	// 수집 날짜 (크롤링 시각)
	@Column(name = "crawled_at", nullable = false)
	private LocalDateTime crawled_at;

	// 게시글 상세 id - FK
	@OneToOne
	@JoinColumn(name = "article_detail_id", nullable = false, unique = true)
	private ArticleDetail articleDetail;

	// 게시글-토픽 관계 테이블
	@ManyToMany
	@JoinTable(
		name = "article_topic",
		joinColumns = @JoinColumn(name = "article_id", nullable = false),
		inverseJoinColumns = @JoinColumn(name = "topic_id", nullable = false)
	)
	private List<Topic> topics = new ArrayList<>();
}
