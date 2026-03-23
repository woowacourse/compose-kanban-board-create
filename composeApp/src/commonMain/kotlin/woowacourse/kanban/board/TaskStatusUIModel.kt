package woowacourse.kanban.board

import androidx.compose.ui.graphics.Color
import woowacourse.kanban.board.data.TaskStatus


enum class TaskStatusUIModel(
    val headerColor: Color,
    val backgroundColor: Color,
    val borderColor: Color,
    val text: String,
) {
    TO_DO(
        headerColor = CustomColor.TODO_CARD_HOLDER_HEADER_COLOR.color,
        backgroundColor = CustomColor.TODO_CARD_HOLDER_BACKGROUND_COLOR.color,
        borderColor = CustomColor.TODO_CARD_HOLDER_BORDER_COLOR.color,
        text = "To Do",
    ),
    IN_PROGRESS(
        headerColor = CustomColor.IN_PROGRESS_CARD_HOLDER_HEADER_COLOR.color,
        backgroundColor = CustomColor.IN_PROGRESS_CARD_HOLDER_BACKGROUND_COLOR.color,
        borderColor = CustomColor.IN_PROGRESS_CARD_HOLDER_BORDER_COLOR.color,
        text = "In Progress",
    ),
    DONE(
        headerColor = CustomColor.DONE_CARD_HOLDER_HEADER_COLOR.color,
        backgroundColor = CustomColor.DONE_CARD_HOLDER_BACKGROUND_COLOR.color,
        borderColor = CustomColor.DONE_CARD_HOLDER_BORDER_COLOR.color,
        text = "Done",
    );

    companion object {
        fun TaskStatusUIModel.toDomain(): TaskStatus {
            return when (this) {
                TaskStatusUIModel.TO_DO -> TaskStatus.TO_DO
                TaskStatusUIModel.IN_PROGRESS -> TaskStatus.IN_PROGRESS
                TaskStatusUIModel.DONE -> TaskStatus.DONE
            }
        }

        fun TaskStatus.toUIModel(): TaskStatusUIModel {
            return when (this) {
                TaskStatus.TO_DO -> TO_DO
                TaskStatus.IN_PROGRESS -> IN_PROGRESS
                TaskStatus.DONE -> DONE
            }
        }
    }
}
