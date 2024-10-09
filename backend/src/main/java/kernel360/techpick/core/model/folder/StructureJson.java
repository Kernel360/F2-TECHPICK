package kernel360.techpick.core.model.folder;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import kernel360.techpick.core.model.common.TimeTracking;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.structure.service.Structure;
import kernel360.techpick.feature.structure.service.node.server.ServerNode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "folder_structure")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StructureJson extends TimeTracking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(columnDefinition = "longblob", nullable = false)
	private Structure<ServerNode> structure;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	private StructureJson(Structure<ServerNode> structure, User user) {
		this.structure = structure;
		this.user = user;
	}

	public void updateStructure(Structure<ServerNode> structure) {
		this.structure = structure;
	}

	// TODO: 엔티티 사용자가 정적 팩토리 메소드로 필요한 함수를 구현 하세요
	public static StructureJson create(Structure<ServerNode> structure, User user) {
		return new StructureJson(structure, user);
	}
}
