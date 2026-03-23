package woowacourse.kanban.board.component

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import org.junit.Test
import woowacourse.kanban.board.component.task.TaskCard

@OptIn(ExperimentalTestApi::class)
class TaskCardTest {
    @Test
    fun `데이터가 주어지면 제목, 내용, 닉네임을 모두 표시`() = runComposeUiTest {
        setContent {
            TaskCard(
                title = "안드로이드",
                script = "UI 테스트",
                tags = listOf("Compose", "Test"),
                nickname = "Sam",
            )
        }

        onNodeWithText("안드로이드").assertExists()
        onNodeWithText("UI 테스트").assertExists()
        onNodeWithText("Sam").assertExists()
    }

    @Test
    fun `태그가 비어있으면 해당 태그 컴포넌트를 생성하지 않는다`() = runComposeUiTest {
        val title = "hh"
        val script = "ss"
        val emptyTags = emptyList<String>()
        val nickname = "sam"

        setContent {
            TaskCard(
                title = title,
                script = script,
                tags = emptyTags,
                nickname = nickname,
            )
        }

        onNodeWithTag("tags_area").assertDoesNotExist()
    }

    @Test
    fun `본문이 공백으로 이뤄진다면 해당 본문 컴포넌트를 생성하지 않는다`() = runComposeUiTest {
        val title = "hh"
        val script = "    "
        val tags = listOf<String>("tag", "tag2")
        val nickname = "sam"

        setContent {
            TaskCard(
                title = title,
                script = script,
                tags = tags,
                nickname = nickname,
            )
        }

        onNodeWithTag("script_area").assertDoesNotExist()
    }

    @Test
    fun `본문이 비어있으면 해당 컴포넌트를 생성하지 않는다`() = runComposeUiTest {
        val title = "hh"
        val script = ""
        val tags = listOf<String>("tag", "tag2")
        val nickname = "sam"

        setContent {
            TaskCard(
                title = title,
                script = script,
                tags = tags,
                nickname = nickname,
            )
        }

        onNodeWithTag("script_area").assertDoesNotExist()
    }

    @Test
    fun `본문과 태그가 비어있으면 해당 컴포넌트를 생성하지 않는다`() = runComposeUiTest {
        val title = "hh"
        val script = ""
        val tags = emptyList<String>()
        val nickname = "sam"

        setContent {
            TaskCard(
                title = title,
                script = script,
                tags = tags,
                nickname = nickname,
            )
        }

        onNodeWithTag("tags_area").assertDoesNotExist()
        onNodeWithTag("script_area").assertDoesNotExist()
    }
}
