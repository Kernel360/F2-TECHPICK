package kernel360.techpick.folder.service.structure;

public class StructureTestCase {

	/**
	 * 3개의 폴더에 대한 예외 케이스
	 */
	// json 에 2가지 폴더 타입이 모두 없는 경우
	public static final String CASE_INVALID_A = """
	{ "root": [] }";
	""";
	// 미분류 폴더에 하위 폴더가 포함 되어 있는 경우
	public static final String CASE_INVALID_B = """
		{
			"root": [],
			"recycleBin": [
				 { "id":"1", "type":"folder", "folderId":1, "name":"폴더", "children": null },
			]
		}
		""";
	// json 자체가 배열로 들어 오는 경우
	public static final String CASE_INVALID_C = """
		[
			"root": { "id":"1", "type":"folder", "folderId":1, "name":"폴더", "children": null },
			recycleBin: []
		]
	""";
	// null 값이 들어 있는 경우
	public static final String CASE_INVALID_D = """
		{
		    "root": [
		        {
		            "id": null,
		            "type": "folder",
		            "folderId": null,
		            "name": null,
		            "children": null
		        },
		    ],
		    "recycleBin": []
		}
		""";
	// null 이 들어 있을 경우
	public static final String CASE_INVALID_E = """
		{ "root": null, "recycleBin": [] }
		""";
	// 빈 정보일 경우
	public static final String CASE_INVALID_F = """
		{
		    "root": [
		        {},
		    ],
		    "recycleBin": []
		}
		""";
	// 필요한 정보가 타입이 잘못된 경우 (TYPE = FOLDER | PICK)
	public static final String CASE_INVALID_G = """
	   	{
		    "root": [
		        {
		            "id": "hello",
		            "type": "SOME_WRONG_TYPE",
		            "folderId": 2,
		            "name": "some_name",
		            "children": []
		        },
		    ],
		    "recycleBin": []
		}
	""";
	// 필요한 정보가 타입이 잘못된 경우 (children != array)
	public static final String CASE_INVALID_H = """
		{
		    "root": [
		        {
		            "id": "hello",
		            "type": "folder",
		            "folderId": 2,
		            "name": "some_name",
		            "children": { "id: "foo" }
		        },
		    ],
		    "recycleBin": []
		}
	""";
	// id 값이 문자 열이 아닌 경우 (프론트엔드가 string으로 처리)
	public static final String CASE_INVALID_I = """
		{
		    "root": [
		        {
		            "id": 12,
		            "type": "folder",
		            "folderId": 2,
		            "name": "some_name",
		            "children": []
		        },
		    ],
		    "recycleBin": []
		}
		""";
	// 필요한 정보가 일부 없는 경우 ( type 이 없음 )
	public static final String CASE_INVALID_J = """
		{
		    "root": [
		        {
		            "id": 12,
		            "folderId": 2,
		            "name": "some_name",
		            "children": []
		        },
		    ],
		    "recycleBin": []
		}
		""";

	/**
	 * 정상 테스트 케이스
	 */

	// 정상 케이스 0 - 빈 배열은 OK
	public static final String CASE_OK_EMPTY_ARRAY_OR_NULL = """
		{ "root": [], "recycleBin": [] }
		""";

	// 정상 케이스 1
	public static final String CASE_OK_SIMPLE = """
		{
		   "root": [
		     { "id":"1", "type":"folder", "folderId":1, "name":"폴더1", "children": [] },
		     { "id":"2", "type":"pick", "pickId":2, "name":"픽 이름1" },
		     { "id":"3", "type":"folder", "folderId":3, "name":"폴더2", "children": [
		       { "id":"4", "type":"pick", "pickId":4, "name":"픽 이름2" },
		       { "id":"5", "type":"folder", "folderId":5, "name":"폴더3", "children": [] }
		     ] }
		   ],
		   "recycleBin": []
		 }
	""";
	// 정상 케이스 2
	public static final String CASE_OK_LONG = """
		{
		     "root": [
		         {
		             "id": "1",
		             "type": "folder",
		             "folderId": 1,
		             "name": "Favorites",
		             "children": []
		         },
		         {
		             "id": "2",
		             "type": "folder",
		             "folderId": 2,
		             "name": "Frontend",
		             "children": [
		                 {
		                     "id": "3",
		                     "type": "folder",
		                     "folderId": 3,
		                     "name": "React",
		                     "children": [
		                         {
		                             "id": "4",
		                             "type": "pick",
		                             "pickId": 4,
		                             "name": "React Hooks"
		                         }
		                     ]
		                 },
		                 {
		                     "id": "5",
		                     "type": "folder",
		                     "folderId": 5,
		                     "name": "TypeScript",
		                     "children": []
		                 }
		             ]
		         }
		     ],
		     "recycleBin": []
		 }
	""";
}
