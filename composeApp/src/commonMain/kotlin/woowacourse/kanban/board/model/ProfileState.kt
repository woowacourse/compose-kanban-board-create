package woowacourse.kanban.board.model

import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.profile
import org.jetbrains.compose.resources.DrawableResource

enum class ProfileState(val text: String, val icon: DrawableResource) {
    DINO(text = "다이노", icon = Res.drawable.profile),
    PAMES(text = "페임스", icon = Res.drawable.profile),
}
