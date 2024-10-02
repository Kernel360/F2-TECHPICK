package kernel360.techpick.feature.folder.service.parser.structure;

import java.util.List;

public interface StructureParser {

	// doParse의 결과물을 바탕으로 Validation 작업 진행
	List<StructureNode> doParse(String json);
}
