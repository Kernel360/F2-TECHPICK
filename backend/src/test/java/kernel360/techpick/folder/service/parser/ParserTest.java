package kernel360.techpick.folder.service.parser;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import kernel360.techpick.feature.structure.exception.ApiDirectoryException;
import kernel360.techpick.feature.structure.service.Structure;
import kernel360.techpick.feature.structure.service.model.StructureMapper;
import kernel360.techpick.feature.structure.service.node.client.ClientNode;
import kernel360.techpick.feature.structure.service.node.server.RelationalNode;
import kernel360.techpick.feature.structure.service.node.server.ServerNode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParserTest {

	StructureMapper structureMapper;

	@BeforeEach
    public void setUp() {
		structureMapper = new StructureMapper( new DummyNameProvider() );
	}

	@Test
	@DisplayName("직렬화 - 비직렬화 테스트")
	public void input_json_to_object_to_json() {
		// given
		String json = ParserTestCase.CASE_OK_SIMPLE;

		// 1. 클라이언트가 보낸 JSON 구조를 서버에서 필요한 정보만 파싱 및 구조 검증 합니다.
		//    서버에서 필요한 필수 필드가 없는 경우 예외를 던집니다.
		Structure<ServerNode> deserializedFromClient = Structure.fromJson(json, ServerNode.class);

		// 2. 포맷 검사가 끝났으니 DB에 직렬화 해서 저장합니다.
		String serialized = deserializedFromClient.serialize();
		log.info(serialized);

		// 3. DB에 저장한 구조 json을 DB에서 불러옵니다.
		Structure<ServerNode> deserializedFromDB = Structure.fromJson(serialized, ServerNode.class);

		// 4. 클라이언트에게 보내기 위한 객체(Structure<ClientNode>)로 변환하면서, 필요한 필드를 추가합니다.
		//    ex. name 값이 여기서 들어갑니다.
		Structure<ClientNode> dataForClient = structureMapper.toClientStructure(deserializedFromDB);

		// 4. 직렬화해서 클라이언트에게 전송 합니다.
		//    아래처럼 안해도, 그냥 jackson이 알아서 하긴 하는데 일단 메소드를 만들었음.
		String finalResultForClient = dataForClient.serialize();
		log.info(finalResultForClient);
	}

	@Test
	@DisplayName("간단한 구조 테스트")
	public void parse_json_string_test() {
		// given
		String json = ParserTestCase.CASE_OK_SIMPLE;
		// when
		Structure<ServerNode> structure = Structure.fromJson(json, ServerNode.class);
		List<RelationalNode> nodes = structure.convertRootToNodeList(0L);
		// then
		assertThat(nodes).hasSize(5);
		for (RelationalNode node : nodes) {
			assertThat(node).hasNoNullFieldsOrProperties();
		}
		System.out.println(nodes);
	}

	@Test
	@DisplayName("실제 프론트가 보낼 예제 테스트")
	public void parse_json_with_real_data() {
		// given
		String json = ParserTestCase.CASE_OK_LONG;
		// when
		Structure<ServerNode> structure = Structure.fromJson(json, ServerNode.class);
		List<RelationalNode> nodes = structure.convertRootToNodeList(0L);
		// then
		assertThat(nodes).hasSize(5);
		for (RelationalNode node : nodes) {
			assertThat(node).hasNoNullFieldsOrProperties();
		}
		System.out.println(nodes);
	}

	@Test
	@DisplayName("root에 빈 배열이 들어오면 정상적으로 처리, 빈 배열 반환")
	public void parse_json_no_folder() {
		// given
		String json = ParserTestCase.CASE_OK_EMPTY_ARRAY_OR_NULL;
		// when
		Structure<ServerNode> structure = Structure.fromJson(json, ServerNode.class);
		List<RelationalNode> nodes = structure.convertRootToNodeList(0L);
		// then
		assertThat(nodes).isEmpty();
	}


	@Nested
	@DisplayName("잘못된 Json 구조를 보냈을 경우 ApiFolderException을 발생시켜야 합니다.")
	class WrongJsonStructureExceptionTest {

		@Test
		@DisplayName("잘못된 구조/정보가 입력될 경우 - A")
		public void parse_json_wrong_format_1() {
			// given
			String json = ParserTestCase.CASE_INVALID_A;
			// when + then
			assertThatExceptionOfType(ApiDirectoryException.class).isThrownBy(
				() -> Structure.fromJson(json, ServerNode.class)
			);
		}

		@Test
		@DisplayName("잘못된 구조/정보가 입력될 경우 - B")
		public void parse_json_wrong_format_2() {
			// given
			String json = ParserTestCase.CASE_INVALID_B;
			// when + then
			assertThatExceptionOfType(ApiDirectoryException.class).isThrownBy(
				() -> Structure.fromJson(json, ServerNode.class)
			);
		}

		@Test
		@DisplayName("잘못된 구조/정보가 입력될 경우 - C")
		public void parse_json_wrong_format_3() {
			// given
			String json = ParserTestCase.CASE_INVALID_C;
			// when + then
			assertThatExceptionOfType(ApiDirectoryException.class).isThrownBy(
				() -> Structure.fromJson(json, ServerNode.class)
			);
		}

		@Test
		@DisplayName("잘못된 구조/정보가 입력될 경우 - D")
		public void parse_json_wrong_format_4() {
			// given
			String json = ParserTestCase.CASE_INVALID_D;
			// when + then
			assertThatExceptionOfType(ApiDirectoryException.class).isThrownBy(
				() -> Structure.fromJson(json, ServerNode.class)
			);
		}

		@Test
		@DisplayName("잘못된 구조/정보가 입력될 경우 - E")
		public void parse_json_wrong_format_5() {
			// given
			String json = ParserTestCase.CASE_INVALID_E;
			// when + then
			assertThatExceptionOfType(ApiDirectoryException.class).isThrownBy(
				() -> Structure.fromJson(json, ServerNode.class)
			);
		}

		@Test
		@DisplayName("잘못된 구조/정보가 입력될 경우 - F")
		public void parse_json_wrong_format_6() {
			// given
			String json = ParserTestCase.CASE_INVALID_F;
			// when + then
			assertThatExceptionOfType(ApiDirectoryException.class).isThrownBy(
				() -> Structure.fromJson(json, ServerNode.class)
			);
		}

		@Test
		@DisplayName("잘못된 구조/정보가 입력될 경우 - G")
		public void parse_json_wrong_format_7() {
			// given
			String json = ParserTestCase.CASE_INVALID_G;
			// when + then
			assertThatExceptionOfType(ApiDirectoryException.class).isThrownBy(
				() -> Structure.fromJson(json, ServerNode.class)
			);
		}

		@Test
		@DisplayName("잘못된 구조/정보가 입력될 경우 - H")
		public void parse_json_wrong_format_8() {
			// given
			String json = ParserTestCase.CASE_INVALID_H;
			// when + then
			assertThatExceptionOfType(ApiDirectoryException.class).isThrownBy(
				() -> Structure.fromJson(json, ServerNode.class)
			);
		}

		@Test
		@DisplayName("잘못된 구조/정보가 입력될 경우 - I")
		public void parse_json_wrong_format_9() {
			// given
			String json = ParserTestCase.CASE_INVALID_I;
			// when + then
			assertThatExceptionOfType(ApiDirectoryException.class).isThrownBy(
				() -> Structure.fromJson(json, ServerNode.class)
			);
		}

		@Test
		@DisplayName("잘못된 구조/정보가 입력될 경우 - J")
		public void parse_json_wrong_format_10() {
			// given
			String json = ParserTestCase.CASE_INVALID_J;
			// when + then
			assertThatExceptionOfType(ApiDirectoryException.class).isThrownBy(
				() -> Structure.fromJson(json, ServerNode.class)
			);
		}
	}
}
