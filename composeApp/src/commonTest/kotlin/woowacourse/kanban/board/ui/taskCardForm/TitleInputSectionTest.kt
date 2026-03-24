package woowacourse.kanban.board.ui.taskCardForm

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.runComposeUiTest
import woowacourse.kanban.board.model.ValidationMessages
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalTestApi::class)
class TitleInputSectionTest {
    @Test
    fun `제목 입력은 상태를 갱신한다`() = runComposeUiTest {
        var title by mutableStateOf("")

        setContent {
            MaterialTheme {
                TitleInputSection(
                    title = title,
                    onTitleChange = { title = it },
                    errorMessage = if (title.isBlank()) ValidationMessages.TITLE_REQUIRED else null,
                )
            }
        }

        onNode(hasSetTextAction()).performTextInput("제목입니다")

        runOnIdle {
            assertEquals("제목입니다", title)
        }
    }

    @Test
    fun `제목이 있으면 포커스를 잃어도 에러 메시지를 표시하지 않는다`() = runComposeUiTest {
        var title by mutableStateOf("")

        setContent {
            MaterialTheme {
                Column {
                    TitleInputSection(
                        title = title,
                        onTitleChange = { title = it },
                        errorMessage = if (title.isBlank()) ValidationMessages.TITLE_REQUIRED else null,
                    )
                    Button(onClick = {}) { Text("다른 곳") }
                }
            }
        }

        onNodeWithTag("titleInput")
            .performTextInput("제목")

        onNodeWithText("다른 곳").performClick()

        onNodeWithText("제목을 입력해 주세요.", useUnmergedTree = true)
            .assertDoesNotExist()
    }

}
