package kernel360.techpick.feature.api.pick.controller;

public interface PickApiSpecification {

    // [해당 링크를 픽한 적이 있는지 확인]
    // GET api/picks/link/{url}
    // -------------------- no request body
    // -------------------- Response Body = { pick_id<?> }
    //                                        pick_id가 없으면, pick을 안한 url

    // [ 픽 조회 ]
    // GET api/picks/{pick_id}
    // -------------------- Request Body = X
    // -------------------- Response Body = { name, parent_folder_id, 태그 ... 등등 }

    // [ 픽 정보 수정 ]
    // PUT api/picks/{pick_id}
    // -------------------- Request Body = { name, title, tags ... }

    // [ 픽 위치 수정 ]
    // PATCH api/picks/{pick_id}
    // -------------------- Request Body = {
    //                                       parent_folder_id가 있다면 위치도 변경
    //                                       destination_position_idx : 0이면 최상단 위치로
    //                                      }

    // [ 픽 위치 생성 ]
    // POST api/picks/
    // -------------------- Request Body = {
    //                                       name : 픽 이름
    //                                       태그 정보 ...
    //                                       parent_folder_id : 생성 위치
    //                                       destination_position_idx <?> : 0이면 최상단 위치, 없으면 제일 마지막.
    //                                      }

    // [ 픽 삭제 조회 ]
    // GET api/picks/{pick_id}
    // -------------------- no request body
    // -------------------- no response body
}
