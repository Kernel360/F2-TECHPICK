package kernel360.techpick.core.feature.user;

import java.time.LocalDateTime;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kernel360.techpick.core.common.model.TimeTracking;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@SQLDelete(sql = "UPDATE user SET deleted_at = CURRENT_TIMESTAMP WHERE user_id=?")
@SQLRestriction("deleted_at IS NULL")
@Table(name = "user")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends TimeTracking /* implements UserDetails --> 시큐리티 도입시 추가 */ {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	// 닉네임 (없으면 랜덤 생성 - Ex. "노래하는피치#145")
	// TODO: 닉네임을 unique로 잡을지 토의 후 결정 (사용자 검색 시 이걸 id로 사용)
	@Column(name = "nickname", nullable = false /*, unique = true */)
	private String nickname;

	// 이메일 (WARN: 두 소셜 로그인이 같은 이메일을 가질 수 있음)
	@Column(name = "email", nullable = false)
	private String email;

	// 유저 권한
	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;

	// 비밀번호 (소셜 로그인 사용자는 null)
	@Column(name = "password") // nullable
	private String password;

	// 소셜 제공자 (null일 경우 자체 가입 회원)
	@Enumerated(EnumType.STRING)
	@Column(name = "social_provider") // nullable
	private SocialType socialProvider;

	// 소셜 제공자 id
	@Column(name = "social_provider_id") // nullable
	private String socialProviderId;

	// Soft Delete - 삭제 시간
	@Column(name = "deleted_at") // nullable
	private LocalDateTime deletedAt;

	// TODO: 전화번호 - 일단 넣었으며, 토의 후 결정.
	@Column(name = "tel") // nullable
	private String tel;

	// TODO: 연령대 - 일단 넣었으며, 토의 후 결정.
	@Column(name = "age_group") // nullable
	@Enumerated(EnumType.STRING)
	private AgeGroup ageGroup;

	// TODO: 직군 분류 - 일단 넣었으며, 토의 후 결정.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "job_group_id") // nullable
	private JobGroup jobGroup;

	// TODO: 엔티티 사용자가 정적 팩토리 메소드로 필요한 함수를 구현 하세요

	private User(
		SocialType socialProvider,
		String socialProviderId,
		String nickname,
		String password,
		String email,
		Role role
	) {
		this.socialProviderId = socialProviderId;
		this.socialProvider = socialProvider;
		this.nickname = nickname;
		this.password = password;
		this.email = email;
		this.role = role;
	}
}
