package kernel360.techpick.entity.admin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// NOTE: 관리자만 수정 가능한 테이블 입니다.
// TODO: 기본 값은 "분류 없음" 레코드 입니다.
//       '분류 없음'을 테이블에 반드시 존재하도록 설정해야 합니다.
@Table(name = "topic")
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Topic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "topic_id")
	private Long topicId;

	// 주제명 (중복된 주제 명은 없어야 합니다.)
	@Column(name = "topic_name", nullable = false, unique = true)
	private String topicName;
}
