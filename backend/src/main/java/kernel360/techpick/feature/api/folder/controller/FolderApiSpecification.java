package kernel360.techpick.feature.api.folder.controller;

public interface FolderApiSpecification {

    // [ 최초 필수 폴더 정보 획득 (폴더가 닫힌 상태 임을 가정) ]
    //   클라이언트에서 예외 발생시, 모두 강제로 폴더가 닫히도록 유도
    //
    // GET api/folders/
    // -------------------- No Request Body
    // -------------------- Response Body = [
    //                                        type, --> root | unclassified | recycle
    //                                        folder_id,
    //                                        name,
    //                                       ]

    // [ 폴더의 자식 정보 획득 ]
    //    이때, 폴더의 이름 + 자식 구조 1 depth 까지만 결과를 반환.
    //    클라이언트에서 펼처진 폴더에 대한 GET 요청을 연쇄적으로 날릴 것.
    // GET api/folders/children/{folder_id}
    // -------------------- No Request Body
    // -------------------- Response Body = { List of "depth-1 child" folder (id & name) / pick (id & title) }

    // [ 폴더 정보 수정 ]
    // PATCH api/folders/data/{folder_id}
    // -------------------- Request Body = {
    //                                       name : nullable --> null일 경우 아무 것도 안하며, 빈 문자열일 경우 예외 발생
    //                                      }
    // -------------------- No Response Body

    // [ 폴더 위치 수정 - 값이 null일 때 "생성"과 다르게 적용되는 점 주의 ]
    // PATCH api/folders/location/{folder_id}/location
    // -------------------- Request Body = {
    //                                       parent_folder_id: nullable --> 있다면 해당 폴더로 소속되게, 없으면 변경 없음
    //                                       order_idx : nullable --> 0이면 폴더중 최상단 위치, 없으면 변경 없음
    //                                      }
    // -------------------- No Response Body
    // * 이때, parent_folder_id가 휴지통에 있는 id거나 휴지통 id 일 수 있다.
    // * 휴지통으로 이동하는 것은 삭제로 처리하지 않는다.

    // [ 폴더 생성 ]
    // POST api/folders/
    // -------------------- Request Body = {
    //                                       data: {
    //                                         name : <!!필수값!!> --> 빈 문자열이거나, null일 경우 예외 발생
    //                                       },
    //                                       location: {
    //                                         parent_folder_id: nullable --> 있다면 해당 폴더로 소속되게, 없으면 root로
    //                                         order_idx : nullable --> 0이면 폴더중 최상단 위치, 없으면 제일 마지막.
    //                                       }
    //                                     }
    //
    // -------------------- Response Body = { created_folder_id }

    // [폴더 삭제 - 휴지통으로 부터 삭제]
    // DELETE api/folders/{folder_id}
    // -------------------- no request body
    // -------------------- no response body
}
