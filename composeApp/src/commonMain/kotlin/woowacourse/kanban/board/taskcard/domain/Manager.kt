package woowacourse.kanban.board.taskcard.domain

import woowacourse.kanban.board.constant.ManagerButtonConst

enum class Manager {
    DINO,
    FAMES,
}

fun Manager.value(): String {
    return if(this == Manager.DINO) ManagerButtonConst.MANAGER_BUTTON_DINO else ManagerButtonConst.MANAGER_BUTTON_PAMES
}
