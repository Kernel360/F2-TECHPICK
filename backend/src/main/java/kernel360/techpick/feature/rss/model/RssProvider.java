package kernel360.techpick.feature.rss.model;

import java.util.List;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.rss.RssRawData;
import kernel360.techpick.core.model.rss.RssSupportingBlog;
import kernel360.techpick.feature.rss.repository.RssBlogRepository;
import kernel360.techpick.feature.rss.repository.RssRawDataRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RssProvider {

	private final RssBlogRepository rssBlogRepository;
	private final RssRawDataRepository rssRawDataRepository;

	public List<RssRawData> findAllRawData() {
		return rssRawDataRepository.findAll();
	}

	public List<RssRawData> saveAllRawData(List<RssRawData> rawData) {
		return rssRawDataRepository.saveAll(rawData);
	}

	public List<RssSupportingBlog> findAllBlog() {
		return rssBlogRepository.findAll();
	}

}
