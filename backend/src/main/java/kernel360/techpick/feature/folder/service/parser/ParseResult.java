package kernel360.techpick.feature.folder.service.parser;

import java.util.List;

import kernel360.techpick.feature.folder.service.parser.action.FolderAction;
import kernel360.techpick.feature.folder.service.parser.structure.StructureNode;

/**
 * Memo:
 * 1. 트랜잭션 시작
 * 2. FolderCreateRequest 를 Folder 테이블에 적용 ( action.run( DB 혹은 서비스 주입 ) )
 * 3. 적용된 테이블 - List<StructureNode> 둘을 비교
 * 4. 검증 실패시 예외 발생, rollback
 */
public class ParseResult {
	private List<StructureNode> nodes;
	private FolderAction action;
}
