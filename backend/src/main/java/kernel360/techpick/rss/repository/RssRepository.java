package kernel360.techpick.rss.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kernel360.techpick.entity.article.RawCrawledArticle;

/**
 * 파이썬 서버 구현 시 삭제 예정
 */
public interface RssRepository extends JpaRepository<RawCrawledArticle, Long> {

	@Query("SELECT r.link FROM RawCrawledArticle r")
	List<String> findAllLinks();
}
