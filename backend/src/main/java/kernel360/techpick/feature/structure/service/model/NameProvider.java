package kernel360.techpick.feature.structure.service.model;

// 테스트 코드를 위해 인터페이스를 분리했습니다.
public interface NameProvider {

    String findPickNameById(Long id);

    String findFolderNameById(Long id);
}
