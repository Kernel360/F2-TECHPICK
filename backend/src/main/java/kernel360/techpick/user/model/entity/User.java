package kernel360.techpick.user.model.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kernel360.techpick.common.model.entity.CreatedAndUpdatedTimeColumn;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@SQLDelete(sql = "UPDATE user SET deleted_at = CURRENT_TIMESTAMP WHERE user_id=?")
@SQLRestriction("deleted_at IS NULL")
@Table(name = "user")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends CreatedAndUpdatedTimeColumn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	// 소셜 로그인 이메일
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	// Soft Delete - 삭제 시간
	@Column(name = "deleted_at")
	private LocalDateTime deletedAt; // nullable

	public User(String email, LocalDateTime deletedAt) {
		this.email = email;
		this.deletedAt = deletedAt;
	}
}
