package kernel360.techpick.entity.admin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * NOTE: 이 부분은 좀 더 토의가 필요 합니다.
 *
 */
@Table(name = "crawling_topic_mapping")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CrawlingTopicMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "crawling_topic_mapping_id", updatable = false)
	private Long crawlingTopicMappingId;

	// TODO: 태그 종류는 블로그별로 다 달라질 것이다.
	//       아래 처럼 하면, 블로그가 추가될 때마다 우리가 직접 매핑해줘야 한다.

	// TODO: 아래와 같이 하면, 블로그별 태그 mapper를 구현할 필요 없음.
	//       아래 mappingString이 공용이기 때문.
	//       ex. "#Spring#스프링#spring" --> 모든 블로그에 적용
	@Column(name = "mapping_string", nullable = false)
	private String mappingString;
}
