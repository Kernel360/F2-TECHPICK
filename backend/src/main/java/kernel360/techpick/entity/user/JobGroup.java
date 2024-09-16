package kernel360.techpick.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

// NOTE: 관리자만 수정 가능한 테이블 입니다.
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "jobgroup_id", updatable = false)
	private Long jobGroupId;

	// 직군 명
	@Column(name = "jobgroup_name", nullable = false)
	private String jobGroupName;
}
