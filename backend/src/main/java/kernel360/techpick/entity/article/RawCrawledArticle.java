package kernel360.techpick.entity.article;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RawCrawledArticle extends CreatedAndUpdatedTimeColumn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "raw_crawled_article_id")
	private Long rawCrawledArticleId;

	// TODO: 아래 크롤링 데이터 칼럼은 토의 후 바뀔 예정 입니다.
	// 크롤링 데이터
	@Column(name = "data", nullable = false)
	private String data;
}
