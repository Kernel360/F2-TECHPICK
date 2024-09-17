package kernel360.techpick.entity.article;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import kernel360.techpick.entity.common.CreatedAndUpdatedTimeColumn;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * NOTE: 크롤링한 Raw 데이터를 삽입하는 테이블입니다.
 *       데이터 형식은 바뀔 수 있습니다.
 */
@Table(name = "raw_crawled_article")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RawCrawledArticle extends CreatedAndUpdatedTimeColumn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "raw_crawled_article_id")
	private Long rawCrawledArticleId;

	// TODO: 변수명은 변경 될 수 있습니다.
	@Column(name = "title")
	private String title;

	@Column(name = "link", columnDefinition = "LONGBLOB")
	private String link;

	@Column(name = "pubDate")
	private String pubDate;

	@Column(name = "creator")
	private String creator;

	@Column(name = "joinedCategories")
	private String joinedCategories;

	public RawCrawledArticle(String title, String link, String pubDate, String creator, String joinedCategories) {
		this.title = title;
		this.link = link;
		this.pubDate = pubDate;
		this.creator = creator;
		this.joinedCategories = joinedCategories;
	}
}
