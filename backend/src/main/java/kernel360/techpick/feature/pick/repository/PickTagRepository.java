package kernel360.techpick.feature.pick.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kernel360.techpick.core.model.pick.PickTag;

// TODO: tag 삭제 시 pick이 존재하는지 여부 확인을 위해 임시로 PickTagRepository 생성
// 이후 PickTagProvider 가 구현될 경우 책임 이관 예정
@Repository
public interface PickTagRepository extends JpaRepository<PickTag, Long> {

	boolean existsByTag_Id(Long tag_id);

	void deleteByTag_Id(Long tag_id);
}
