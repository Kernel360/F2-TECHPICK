package kernel360.techpick.feature.rss.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kernel360.techpick.core.model.rss.RssRawData;

public interface RssRawDataRepository extends JpaRepository<RssRawData, Long> {

}
