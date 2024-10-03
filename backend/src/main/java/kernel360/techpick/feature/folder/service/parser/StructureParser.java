package kernel360.techpick.feature.folder.service.parser;

import java.util.List;

import kernel360.techpick.feature.folder.exception.ApiFolderException;
import kernel360.techpick.feature.folder.service.parser.internal.StructureNode;

/**
 * 1. 트랜잭션 시작
 * 2. FolderCreateRequest 를 Folder 테이블에 적용.
 * 3. 적용된 테이블 - List<StructureNode> 둘을 비교
 * 4. 검증 실패시 예외 발생, rollback
 * */
public interface StructureParser {

	/**
	 * @param json 직렬화된 json 문자열
	 * @throws ApiFolderException
	 *         - 유효하지 않는 json 포맷일 경우 예외 발생
	 *         - 서비스에서 정의한 json 포맷과 다를 경우 예외 발생
	 */
	List<StructureNode> parseFolderStructure(String json) throws ApiFolderException;

}
