package woowacourse.kanban.board.component.modal

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test
import woowacourse.kanban.board.Blue50
import woowacourse.kanban.board.ComponentText
import woowacourse.kanban.board.model.modal.Tags
import woowacourse.kanban.board.model.modal.Title

@OptIn(ExperimentalTestApi::class)
class FooterTest {

    @Test
    fun `제목이 입력되면 생성 버튼이 활성화된다`() = runComposeUiTest {
        val title = "제목"
        val isTitleValid = Title.isTitleValid(title)
        val tags = ""
        val isValidTag = Tags.isTagsValid(tags)
        val isButtonEnabled = isTitleValid && isValidTag

        setContent {
            FooterButton(
                enabled = isButtonEnabled,
                containerColor = Blue50,
                text = ComponentText.CREATE_BUTTON,
            )
        }
        onNodeWithText(ComponentText.CREATE_BUTTON)
            .assertIsEnabled()
    }

    @Test
    fun `제목이 입력되지 않으면 생성 버튼이 비활성화된다`() = runComposeUiTest {
        val title = ""
        val isTitleValid = Title.isTitleValid(title)
        val tags = ""
        val isValidTag = Tags.isTagsValid(tags)
        val isButtonEnabled = isTitleValid && isValidTag

        setContent {
            FooterButton(
                enabled = isButtonEnabled,
                containerColor = Blue50,
                text = ComponentText.CREATE_BUTTON,
            )
        }
        onNodeWithText(ComponentText.CREATE_BUTTON)
            .assertIsNotEnabled()
    }

    @Test
    fun `제목이 입력되고 태그가 5개 이상 입력되면 생성 버튼이 비활성화된다`() = runComposeUiTest {
        val title = "제목"
        val isTitleValid = Title.isTitleValid(title)
        val tags = "1,2,3,4,5,6"
        val isValidTag = Tags.isTagsValid(tags)
        val isButtonEnabled = isTitleValid && isValidTag

        setContent {
            FooterButton(
                enabled = isButtonEnabled,
                containerColor = Blue50,
                text = ComponentText.CREATE_BUTTON,
            )
        }
        onNodeWithText(ComponentText.CREATE_BUTTON)
            .assertIsNotEnabled()
    }

    @Test
    fun `제목이 입력되고 5글자 초과인 태그가 입력되면 생성 버튼이 비활성화된다`() = runComposeUiTest {
        val title = "제목"
        val isTitleValid = Title.isTitleValid(title)
        val tags = "1,2,3,1234567899"
        val isValidTag = Tags.isTagsValid(tags)
        val isButtonEnabled = isTitleValid && isValidTag

        setContent {
            FooterButton(
                enabled = isButtonEnabled,
                containerColor = Blue50,
                text = ComponentText.CREATE_BUTTON,
            )
        }
        onNodeWithText(ComponentText.CREATE_BUTTON)
            .assertIsNotEnabled()
    }

    @Test
    fun `제목이 입력되고 5글자 이하인 태그가 5개 이하로 입력되면 생성 버튼이 활성화된다`() = runComposeUiTest {
        val title = "제목"
        val isTitleValid = Title.isTitleValid(title)
        val tags = "1,2,3,123"
        val isValidTag = Tags.isTagsValid(tags)
        val isButtonEnabled = isTitleValid && isValidTag

        setContent {
            FooterButton(
                enabled = isButtonEnabled,
                containerColor = Blue50,
                text = ComponentText.CREATE_BUTTON,
            )
        }
        onNodeWithText(ComponentText.CREATE_BUTTON)
            .assertIsEnabled()
    }
}
