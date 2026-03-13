package woowacourse.kanban.board.ui

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import org.junit.Test
import woowacourse.kanban.board.domain.CardData

@OptIn(ExperimentalTestApi::class)
class CardCreationPanelTest {

    @Test
    fun `제목을 입력하지 않으면, 생성 버튼이 비활성화된다`() = runComposeUiTest {
        // given
        val blankTitle = ""

        //when
        setContent {
            CardCreationPanel(
                onAddItem = { CardData.create(blankTitle, "", listOf(("")), "구름") },
                onShowCardCreationPanel = {},
            )
        }

        //then
        onNodeWithText("생성").assertIsNotEnabled()
    }

    @Test
    fun `제목을 입력하지 않으면 에러메시지가 노출된다`() = runComposeUiTest {
        // given
        val blankTitle = ""

        //when
        setContent {
            CardCreationPanel(
                onAddItem = { CardData.create(blankTitle, "", listOf(("")), "구름") },
                onShowCardCreationPanel = {},
            )
        }

        //then
        onNodeWithText("제목을 입력해 주세요.").assertExists()
    }

    @Test
    fun `태그를 잘못 입력하면, 생성 버튼이 비활성화된다`() = runComposeUiTest {
        // given
        val wrongTag = ",태그"

        //when
        setContent {
            CardCreationPanel(
                onAddItem = { CardData.create("제목", "", wrongTag.split(","), "구름") },
                onShowCardCreationPanel = {},
            )
        }

        //then
        onNodeWithText("생성").assertIsNotEnabled()
    }
}