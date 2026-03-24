package woowacourse.kanban.board.ui.taskBoard

import androidx.compose.ui.graphics.Color
import woowacourse.kanban.board.model.TaskStatus

fun statusToHeaderColor(status: TaskStatus): Color{
    return when(status){
        TaskStatus.TODO -> Color(0xFF155DFC)
        TaskStatus.INPROGRESS -> Color(0xFFE17100)
        TaskStatus.DONE -> Color(0xFF00A63E)
    }
}

fun statusToContentColor(status: TaskStatus): Color{
    return when (status){
        TaskStatus.TODO -> Color(0xFFEFF6FF)
        TaskStatus.INPROGRESS -> Color(0xFFFFFBEB)
        TaskStatus.DONE -> Color(0xFFF0FDF4)
    }
}

fun statusToBorderColor(status: TaskStatus): Color{
    return when (status){
        TaskStatus.TODO -> Color(0xFFBEDBFF)
        TaskStatus.INPROGRESS -> Color(0xFFFEE685)
        TaskStatus.DONE -> Color(0xFFB9F8CF)
    }
}
