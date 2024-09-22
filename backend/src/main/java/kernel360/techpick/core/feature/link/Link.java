package kernel360.techpick.core.feature.link;

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

@Table(name = "link")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Link {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	/**
	 * TODO:
	 * 들어온 URL String을 파싱해서 쿼리를 분리 저장할지, 통으로 할지 토의 필요합니다.
	 * 만약 한다면, Spring의 UriComponent Class를 이용하면 될 것 같습니다.
	 * 지금은 쿼리를 따로 저장해야 할 이유를 못 찾았습니다.
	 * - origin
	 * - path
	 * - query
	 * - etc...
	 * 참고: https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/util/UriComponents.html
	 */

	// URL 출처 (scheme, host name, port)
	@Lob // 한글 도메인 가능성
	@Column(name = "origin", nullable = false, columnDefinition = "BLOB", unique = true)
	private String url;

	// 원문 제목
	@Column(name = "title", nullable = false)
	private String title;

	// 원문 설명
	@Column(name = "description") // nullable
	private String description;

	// TODO: 엔티티 사용자가 정적 팩토리 메소드로 필요한 함수를 구현 하세요

	private Link(String url, String title, String description) {
		this.url = url;
		this.title = title;
		this.description = description;
	}
}
