package kernel360.techpick.feature.rss.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kernel360.techpick.core.model.rss.RssRawData;

/**
 * 파이썬 서버 구현 시 삭제 예정
 */
public interface RssRepository extends JpaRepository<RssRawData, Long> {

	@Query("select r.url from RssRawData r")
	List<String> findAllUrls();
}
