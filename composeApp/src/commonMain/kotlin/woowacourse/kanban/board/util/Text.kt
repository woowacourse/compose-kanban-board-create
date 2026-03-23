package woowacourse.kanban.board.util

import woowacourse.kanban.board.model.Status

object Text {
    const val LABEL_STATUS = "상태 *"
    const val LABEL_TITLE = "제목 *"
    const val LABEL_DESCRIPTION = "설명"
    const val LABEL_TAG = "태그"
    const val LABEL_ASSIGNEE = "담당자 *"

    const val HEADER_CREATE_TASK = "새 태스크 생성"

    const val PLACEHOLDER_TITLE = "태스크 제목을 입력하세요"
    const val PLACEHOLDER_DESCRIPTION = "태스크에 대한 자세한 설명을 입력하세요"

    const val ERROR_TITLE_EMPTY_INPUT = "제목을 입력해주세요"
    const val ERROR_TAG_FORMAT_INVALID = "태그 형식이 올바르지 않습니다."
    const val PLACEHOLDER_TAG = "태그를 쉼표로 구분하여 입력하세요(예: 버그, 긴급)"
    const val HELPER_TAG_LIMIT = "5자 이내의 태그를 최대 5개까지 등록할 수 있습니다."

    const val ACTION_CANCEL = "취소"
    const val ACTION_CREATE = "생성"
    const val ACTION_CLOSE = "닫기"
    const val CONTENT_USER_DEFAULT_IMAGE = "사용자 기본 이미지"
    const val CONTENT_ERROR = "error"
    const val STATUS_TODO = "To Do"
    const val STATUS_IN_PROGRESS = "In Progress"
    const val STATUS_DONE = "Done"
    const val KANBANBOARD_TITLE = "Compose Desktop 칸반 보드"
    const val CREATE_NEW_TASK = "새 테스크 생성"
    const val ALARM_NEW_TASK = "새로운 테스크가 추가되었습니다."

    const val TOTAL_COUNT_IS_ZERO = "완료율 : 0% (0/0)"

    fun formatCompletionRate(doneCount: Int, totalCount: Int): String {
        return "완료율 : ${(doneCount.toFloat() / totalCount * 100).toInt()}% ($doneCount/$totalCount)"
    }

    fun statusLabel(status: Status): String {
        return when (status) {
            Status.TODO -> STATUS_TODO
            Status.INPROGRESS -> STATUS_IN_PROGRESS
            Status.DONE -> STATUS_DONE
        }
    }
}
