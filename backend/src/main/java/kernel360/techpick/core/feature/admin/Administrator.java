package kernel360.techpick.core.feature.admin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kernel360.techpick.core.common.model.CreatedAndUpdatedTimeColumn;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "administrator")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Administrator extends CreatedAndUpdatedTimeColumn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "administrator_id")
	private Long administratorId;

	// 관리자 로그인 이메일
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	// 비밀번호
	@Column(name = "password", nullable = false)
	private String password;

	// 관리자 이름
	@Column(name = "name", nullable = false)
	private String name;

	public Administrator(String email, String password, String name) {
		this.email = email;
		this.password = password;
		this.name = name;
	}
}
