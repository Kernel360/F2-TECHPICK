package kernel360.techpick.core.feature.rss;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import kernel360.techpick.core.common.model.TimeTracking;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "rss_raw_data")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RssRawData extends TimeTracking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	// 제목
	@Column(name = "title", nullable = false)
	private String title;

	// 원문 주소
	// TODO: VARCHAR 최대 크기를 몇으로 할지 토의 필요합니다.
	//       일단 medium 기준 가장 길었던 url 320 글자의 약 2배인 1000 byte로 잡았습니다.
	@Column(name = "url", nullable = false, columnDefinition = "VARCHAR(1000)")
	private String url;

	// 작성 일자
	@Column(name = "published_at", nullable = false)
	private String publishedAt;

	// 요약 설명
	@Column(name = "description") // nullable
	private String description;

	// 작성자
	@Column(name = "creator") // nullable
	private String creator;

	// 복수 카테고리를 쉼표(,)로 구분 (ex. "Foo,Bar,Baz")
	@Column(name = "joined_category") // nullable
	private String joinedCategory;

	// TODO: 엔티티 사용자가 정적 팩토리 메소드로 필요한 함수를 구현 하세요

	private RssRawData(
		String title,
		String url,
		String publishedAt,
		String description,
		String creator,
		String joinedCategory
	) {
		this.title = title;
		this.url = url;
		this.publishedAt = publishedAt;
		this.description = description;
		this.creator = creator;
		this.joinedCategory = joinedCategory;
	}
}
