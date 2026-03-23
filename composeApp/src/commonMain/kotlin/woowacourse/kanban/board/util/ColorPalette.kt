package woowacourse.kanban.board.util

import androidx.compose.ui.graphics.Color

object ColorPalette {
    val Error = Color(0xFFB3261E)
    val AssigneeUnselectedBorder = Color(0xFFE5E7EB)
    val AssigneeSelectedBorder = Color(0xFF615FFF)
    val AssigneeSelectedBackground = Color(0xFFEEF2FF)
    val AssigneeUnselectedBackground = Color(0xFFFFFFFF)
    val StatusSelectedBorder = Color(0xFF1447E6)
    val StatusUnSelectedBorder = Color(0xFFE5E7EB)
    val StatusSelectedBackground = Color(0xFFEEF2FF)
    val StatusUnSelectedBackground = Color(0xFFFFFFFF)
    val StatusSelectedText = Color(0xFF1447E6)
    val StatusUnselectedText = Color(0xFF364153)
    val PlaceHolder = Color(0xFFAAAAAA)
    val ActiveButton = Color(0xFF4F39F6)
    val StatusBarPoint = Color(0xFF4F39F6)
    val StatusBarBackground = Color(0xFFE5E7EB)

    object BoxHeader {
        val Todo = Color(0xFF155DFC)
        val InProgress = Color(0xFFE17100)
        val Done = Color(0xFF00A63E)
    }

    object BoxBody {
        val Todo = Color(0xFFEFF6FF)
        val InProgress = Color(0xFFFFFBEB)
        val Done = Color(0xFFF0FDF4)
    }

    object Border {
        val Todo = Color(0xFFBEDBFF)
        val InProgress = Color(0xFFFEE685)
        val Done = Color(0xFFB9F8CF)
    }
}
