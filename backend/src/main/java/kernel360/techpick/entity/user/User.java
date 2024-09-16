package kernel360.techpick.entity.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kernel360.techpick.entity.admin.Topic;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "user")
@SQLDelete(sql = "UPDATE user SET removed_at = CURRENT_TIMESTAMP WHERE user_id=?")
@SQLRestriction("removed_at IS NULL")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", updatable = false)
	private Long userId;

	// 소셜 로그인 이메일
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	// 연령층 분류
	@Column(name = "age_group", nullable = false)
	@Enumerated(EnumType.STRING)
	private AgeGroup ageGroup;

	// 직군 분류
	@ManyToOne
	@JoinColumn(name = "jobgroup_id", nullable = false)
	private JobGroup jobGroup;

	// Soft Delete - 삭제 시간
	@Column(name = "removed_at")
	private LocalDateTime removedAt; // nullable

	// [사용자 - 관심 토픽] 관계 테이블
	@ManyToMany
	@JoinTable(
		name = "user_topic",
		joinColumns = @JoinColumn(name = "user_id", nullable = false),
		inverseJoinColumns = @JoinColumn(name = "topic_id", nullable = false)
	)
	private List<Topic> topics = new ArrayList<>();
}
