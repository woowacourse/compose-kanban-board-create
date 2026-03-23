package woowacourse.kanban.board.ui.create

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.runComposeUiTest
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class KanbanCreateFooterTest {

    @Test
    fun `제목과 태그에 오류가 없고 제목이 공백이 아닐 경우 활성화된다`() = runComposeUiTest {
        setContent {
            KanbanCreateDialogContent(
                onDismiss = {},
                onCreateConfirm = { _, _ -> },
            )
        }
        onAllNodes(hasSetTextAction())[0]
            .performTextInput("할 일")

        onAllNodes(hasSetTextAction())[2]
            .performTextInput("우테코,테코")

        onNodeWithText("생성")
            .assertIsEnabled()
    }

    @Test
    fun `제목 에러 발생시 비활성화된다`() = runComposeUiTest {
        setContent {
            KanbanCreateDialogContent(
                onDismiss = {},
                onCreateConfirm = { _, _ -> },
            )
        }

        onAllNodes(hasSetTextAction())[0]
            .performTextInput("할 일")

        onAllNodes(hasSetTextAction())[0]
            .performTextClearance()

        onNodeWithText("생성")
            .assertIsNotEnabled()
    }

    @Test
    fun `태그 에러 발생시 비활성화된다`() = runComposeUiTest {
        setContent {
            KanbanCreateDialogContent(
                onDismiss = {},
                onCreateConfirm = { _, _ -> },
            )
        }

        onAllNodes(hasSetTextAction())[2]
            .performTextInput("우아한테크코스")

        onNodeWithText("생성")
            .assertIsNotEnabled()
    }

    @Test
    fun `제목 입력 후 태그 오류를 수정하면 생성 버튼이 다시 활성화된다`() = runComposeUiTest {
        setContent {
            KanbanCreateDialogContent(
                onDismiss = {},
                onCreateConfirm = { _, _ -> },
            )
        }

        val textFields = onAllNodes(hasSetTextAction())

        textFields[0].performTextInput("할 일")
        textFields[2].performTextInput("우아한테크코스")

        onNodeWithText("태그는 5자 이내로 5개까지만 등록할 수 있습니다", useUnmergedTree = true)
            .assertExists()
        onNodeWithText("생성")
            .assertIsNotEnabled()

        textFields[2].performTextClearance()
        textFields[2].performTextInput("버그, 긴급")

        onNodeWithText("태그는 5자 이내로 5개까지만 등록할 수 있습니다", useUnmergedTree = true)
            .assertDoesNotExist()
        onNodeWithText("생성")
            .assertIsEnabled()
    }
}
