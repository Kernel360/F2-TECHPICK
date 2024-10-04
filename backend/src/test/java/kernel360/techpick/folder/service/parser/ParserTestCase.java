package kernel360.techpick.folder.service.parser;

public class ParserTestCase {

	// json 자체가 배열이 아닌 경우
	public static final String CASE_INVALID_A = "{}";
	// json 자체가 잘못된 경우
	public static final String CASE_INVALID_B = "[{]";
	// 빈 정보일 경우
	public static final String CASE_INVALID_C = "[{}]";
	// 필요한 정보가 타입이 잘못된 경우 (TYPE = FOLDER | PICK)
	public static final String CASE_INVALID_D = "{id:\"STR\", type:XXXX, folderId:1, name:\"폴더\", children: null}";
	// 필요한 정보가 타입이 잘못된 경우 (children != array)
	public static final String CASE_INVALID_E = "{id:\"STR\", type:folder, folderId:1, name:\"폴더\", children: {foo:bar}}";
	// id 값이 문자 열이 아닌 경우 (프론트엔드가 string으로 처리)
	public static final String CASE_INVALID_F = "{id:3, type:XXXX, folderId:1, name:\"폴더\", children: {foo:bar}}";
	// 필요한 정보가 일부 없는 경우 (type이 없음)
	public static final String CASE_INVALID_G = "{id:\"STR\", folderId:1, children: null}";
	// 빈 배열은 OK
	public static final String CASE_OK_EMPTY_ARRAY = "[]";
	// 정상 케이스 1
	public static final String CASE_OK_SIMPLE = """
		 [
		 	{id:"1", type:folder, folderId:1, name:"폴더", children: null},
			{id:"2", type:folder, folderId:2, name:"폴더", children: null},
			{id:"3", type:folder, folderId:3, name:"폴더", children: [
					{id:4, type:folder, folderId:4, name:"폴더", children: []},
					{id:5, type:folder, folderId:5, name:"폴더", children: null},
			]}
		 ]
	""";
	// 정상 케이스 2
	public static final String CASE_OK_LONG = """
		[
		   {
		     "id": "1",
		     "type": "folder",
		     "folderId": 1,
		     "name": "Favorites",
		     "children": null
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
		         "children": null
		       }
		     ]
		   }
		 ]
	""";

}
