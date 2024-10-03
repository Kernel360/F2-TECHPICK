package kernel360.techpick.folder.service.parser;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import kernel360.techpick.feature.folder.exception.ApiFolderException;
import kernel360.techpick.feature.folder.service.parser.StructureParser;
import kernel360.techpick.feature.folder.service.parser.internal.StructureNode;
import kernel360.techpick.feature.folder.service.parser.internal.StructureParserImpl;

public class ParserTest {

	/**
	 * NOTE: 사용하지 않는 값이여도 백엔드에 저장하기 전에 json을 한번 검증 해줘야 함.
	 *       parser는 value를 검증할 수 없지만..
	 *       포맷( key, value's type, json format)의 유효성은 검사 가능.
	 *
	 * TODO: 잘못된 json 포맷일 경우 API 예외 던진다.
	 *       1. 만약 json이 배열이 아닌 경우
	 *       2. 필요한 key가 없는 경우
	 *          공통 : id, type, parentId
	 *          폴더일 경우 + children
	 *       3. children이 배열이 아닌 경우
	 * */

	StructureParser structureParser;

	@BeforeEach
	public void beforeEach() {
		structureParser = new StructureParserImpl();
	}

	@Test
	@DisplayName("간단한 구조 테스트")
	public void parse_json_string_test() {
		// given
		String json = ParserTestCase.CASE_OK_SIMPLE;
		// when
		List<StructureNode> structure = structureParser.parseFolderStructure(json);
		// then
		assertThat(structure).hasSize(5);
		for (StructureNode node : structure) {
			assertThat(node).hasNoNullFieldsOrPropertiesExcept("parentFolderId");
		}
	}

	@Test
	@DisplayName("실제 프론트가 보낼 예제 테스트")
	public void parse_json_with_real_data() {
		// given
		String json = ParserTestCase.CASE_OK_LONG;
		// when
		List<StructureNode> structure = structureParser.parseFolderStructure(json);
		// then
		assertThat(structure).hasSize(5);
		for (StructureNode node : structure) {
			assertThat(node).hasNoNullFieldsOrPropertiesExcept("parentFolderId");
		}
	}

	@Test
	@DisplayName("빈 배열이 들어오면 정상적으로 처리, 빈 배열 반환")
	public void parse_json_no_folder() {
		// given
		String json = ParserTestCase.CASE_OK_EMPTY_ARRAY;
		// when
		List<StructureNode> structure = structureParser.parseFolderStructure(json);
		// then
		assertThat(structure).isEmpty();
	}


	@Nested
	@DisplayName("잘못된 Json 구조를 보냈을 경우 ApiFolderException을 발생시켜야 합니다.")
	class WrongJsonStructureExceptionTest {

		@Test
		@DisplayName("잘못된 구조/정보가 입력될 경우")
		public void parse_json_wrong_format_1() {
			// given
			String json = ParserTestCase.CASE_INVALID_A;
			// when + then
			assertThatExceptionOfType(ApiFolderException.class).isThrownBy(
				() -> structureParser.parseFolderStructure(json)
			);
		}

		@Test
		@DisplayName("잘못된 구조/정보가 입력될 경우")
		public void parse_json_wrong_format_2() {
			// given
			String json = ParserTestCase.CASE_INVALID_B;
			// when + then
			assertThatExceptionOfType(ApiFolderException.class).isThrownBy(
				() -> structureParser.parseFolderStructure(json)
			);
		}

		@Test
		@DisplayName("잘못된 구조/정보가 입력될 경우")
		public void parse_json_wrong_format_3() {
			// given
			String json = ParserTestCase.CASE_INVALID_C;
			// when + then
			assertThatExceptionOfType(ApiFolderException.class).isThrownBy(
				() -> structureParser.parseFolderStructure(json)
			);
		}

		@Test
		@DisplayName("잘못된 구조/정보가 입력될 경우")
		public void parse_json_wrong_format_5() {
			// given
			String json = ParserTestCase.CASE_INVALID_E;
			// when + then
			assertThatExceptionOfType(ApiFolderException.class).isThrownBy(
				() -> structureParser.parseFolderStructure(json)
			);
		}

		@Test
		@DisplayName("잘못된 구조/정보가 입력될 경우")
		public void parse_json_wrong_format_6() {
			// given
			String json = ParserTestCase.CASE_INVALID_F;
			// when + then
			assertThatExceptionOfType(ApiFolderException.class).isThrownBy(
				() -> structureParser.parseFolderStructure(json)
			);
		}

		@Test
		@DisplayName("잘못된 구조/정보가 입력될 경우")
		public void parse_json_wrong_format_7() {
			// given
			String json = ParserTestCase.CASE_INVALID_G;
			// when + then
			assertThatExceptionOfType(ApiFolderException.class).isThrownBy(
				() -> structureParser.parseFolderStructure(json)
			);
		}
	}
}
