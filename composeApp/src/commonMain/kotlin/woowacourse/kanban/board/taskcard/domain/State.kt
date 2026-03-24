package woowacourse.kanban.board.taskcard.domain

import woowacourse.kanban.board.constant.StateButtonConst

enum class State {
    TODO(),
    IN_PROGRESS(),
    DONE(),
}

fun State.value(): String {
    return if(this == State.TODO) StateButtonConst.STATE_BUTTON_TODO
        else if (this == State.IN_PROGRESS) StateButtonConst.STATE_BUTTON_PROGRESS
        else StateButtonConst.STATE_BUTTON_DONE
}
