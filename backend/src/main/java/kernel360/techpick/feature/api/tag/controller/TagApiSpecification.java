package kernel360.techpick.feature.api.tag.controller;

public interface TagApiSpecification {

    // [ 나의 태그 리스트 조회 ]
    // GET api/tags/
    // -------------------- Request Body = X
    // -------------------- Response Body = { 태그 리스트 }

    // [ 나의 태그 정보 수정 ]
    // PUT api/tags/
    // -------------------- Request Body =
    // [
    //   {
    //     "tagId": 0,
    //     "tagName": "string",
    //     "tagOrder": 0,
    //     "colorNumber": 0
    //   }
    // ]

    // [ 나의 태그 생성 ]
    // POST api/tags/
    // {
    //   "tagName": "string",
    //   "colorNumber": 0
    // }

    // [ 나의 태그 삭제 ]
    // DELETE api/tags/{tag_id}
    // -------------------- no request body
    // -------------------- no response body

}
