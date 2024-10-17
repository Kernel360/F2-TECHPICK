package kernel360.techpick.feature.api.folder.controller;

public interface FolderApiSpecification {

    // [루트 폴더 정보 획득]
    //    이때, 폴더의 이름 + 자식 구조 1 depth 까지만 결과를 반환.
    //    클라이언트에서 펼처진 폴더에 대한 GET 요청을 연쇄적으로 날릴 것.
    // GET api/folders?type=root
    // -------------------- No Request Body
    // -------------------- Response Body = { name, List of "depth-1 child" folder / pick }

    // [미분류 폴더 정보 획득]
    //    이때, 폴더의 이름 + 자식 구조 1 depth 까지만 결과를 반환.
    //    클라이언트에서 펼처진 폴더에 대한 GET 요청을 연쇄적으로 날릴 것.
    // GET api/folders?type=unclassified
    // -------------------- No Request Body
    // -------------------- Response Body = { name, List of "depth-1 child" folder / pick }

    // [재활용 폴더 정보 획득]
    //    이때, 폴더의 이름 + 자식 구조 1 depth 까지만 결과를 반환.
    //    클라이언트에서 펼처진 폴더에 대한 GET 요청을 연쇄적으로 날릴 것.
    // GET api/folders?type=recycle
    // -------------------- No Request Body
    // -------------------- Response Body = { name, List of "depth-1 child" folder / pick }

    // [타입에 관계 없이 폴더 정보 획득]
    //    이때, 폴더의 이름 + 자식 구조 1 depth 까지만 결과를 반환.
    //    클라이언트에서 펼처진 폴더에 대한 GET 요청을 연쇄적으로 날릴 것.
    // GET api/folders/{folder_id}
    // -------------------- Request Body = { name }
    // -------------------- Response Body = { name, List of "depth-1 child" folder / pick }

    // [폴더 정보 수정]
    // PUT api/folders/{folder_id}
    // -------------------- Request Body = { name, title, tags ... }

    // [폴더 위치 수정]
    // PATCH api/folders/{folder_id}
    // -------------------- Request Body = {
    //                                       parent_folder_id가 있다면 위치도 변경
    //                                       destination_position_idx : 0이면 최상단 위치로
    //                                      }

    // -------------------- No Response Body
    // 이때, parent_folder_id가 휴지통에 있는 id거나 휴지통 id 일 수 있다.
    // 휴지통으로 이동하는 것은 삭제로 처리하지 않는다.

    // [폴더 생성] + 위치, 순서도 함께
    // POST api/folders/
    // -------------------- Request Body = {
    //                                       name : 폴더 이름
    //                                       parent_folder_id : 생성 위치
    //                                       destination_position_idx <?> : 0이면 최상단 위치, 없으면 제일 마지막.
    //                                      }
    // -------------------- Response Body = { created_folder_id }

    // [폴더 삭제 - 휴지통으로 부터 삭제]
    // DELETE api/folders/{folder_id}
    // -------------------- no request body
    // -------------------- no response body
}
