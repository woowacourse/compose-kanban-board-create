## 태스트 상태 전이 규칙
Review 상태를 추가하고, 상태별 태스크의 특징을 적용한다.
To Do: 태스크 삭제 가능. 담당자 미지정 가능
In Progress: 태스크 삭제 가능. 담당자 지정 필수
Review: 태스크 삭제 불가능. 담당자 지정 필수
Done: 태스크 삭제 불가능. 담당자 지정 필수
태스크 상태 전이 규칙을 적용한다. 규칙에 정의되지 않은 상태 전이는 불가능하다.
To Do
└─→ In Progress (작업 시작)

In Progress
├─→ To Do (다시 계획)
└─→ Review (리뷰 요청)

Review
├─→ In Progress (수정 필요)
└─→ Done (승인 완료)

Done
└─→ To Do (재작업)

## 기능 요구 사항
- [x] TODO는 태스크 삭제 가능
- [x] TODO는 담당자 미지정 가능
- [x] TODO는 In Progress로 전이 가능
- [x] TODO는 Review, Done로 전이 불가능

- [x] In Progress는 태스크 삭제 가능
- [x] In Progress는 담당자 지정 필수
- [x] In Progress는 TODO, Review로 전이 가능
- [x] In Progress는 Done로 전이 불가능

- [x] Review는 태스크 삭제 불가능
- [x] Review는 담당자 지정 필수
- [x] Review는 Done, In Progress로 전이 가능
- [x] Review는 TODO로 전이 불가능

- [x] Done은 태스크 삭제 불가능
- [x] Done은 담당자 지정 필수
- [x] Done은 TODO로 전이 가능
- [x] Done은 In Progress, Review로 전이 불가능

- [x] 현재 상태에서 같은 상태로 전이를 시도하면 아무 일도 발생하지 않는다
- [x] 전이가 불가능할 때 예외를 던진다
