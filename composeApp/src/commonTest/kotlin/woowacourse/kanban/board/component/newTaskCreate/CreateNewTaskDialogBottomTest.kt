package woowacourse.kanban.board.component.newTaskCreate

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class CreateNewTaskDialogBottomTest {
    @Test
    fun `취소와 생성 버튼이 노출된다`() = runComposeUiTest {
        setContent {
            CreateNewTaskDialogBottom(
                onClickCloseButton = {},
                onClickCreateButton = {},
                isCreateEnabled = true,
            )
        }

        onNodeWithText("취소").assertIsDisplayed()
        onNodeWithText("생성").assertIsDisplayed()
    }

    @Test
    fun `isCreateEnabled가 false이면 생성 버튼이 비활성화된다`() = runComposeUiTest {
        setContent {
            CreateNewTaskDialogBottom(
                onClickCloseButton = {},
                onClickCreateButton = {},
                isCreateEnabled = false,
            )
        }

        onNodeWithText("생성").assertIsNotEnabled()
    }

    @Test
    fun `isCreateEnabled가 true이면 생성 버튼이 활성화된다`() = runComposeUiTest {
        setContent {
            CreateNewTaskDialogBottom(
                onClickCloseButton = {},
                onClickCreateButton = {},
                isCreateEnabled = true,
            )
        }

        onNodeWithText("생성").assertIsEnabled()
    }

    @Test
    fun `취소 버튼을 클릭하면 onClickCloseButton 콜백이 호출된다`() = runComposeUiTest {
        var isClicked = false
        setContent {
            CreateNewTaskDialogBottom(
                onClickCloseButton = { isClicked = true },
                onClickCreateButton = {},
                isCreateEnabled = true,
            )
        }

        onNodeWithText("취소").performClick()
        assertTrue(isClicked)
    }

    @Test
    fun `생성 버튼을 클릭하면 onClickCreateButton 콜백이 호출된다`() = runComposeUiTest {
        var isClicked = false
        setContent {
            CreateNewTaskDialogBottom(
                onClickCloseButton = {},
                onClickCreateButton = { isClicked = true },
                isCreateEnabled = true,
            )
        }

        onNodeWithText("생성").performClick()
        assertTrue(isClicked)
    }
}
