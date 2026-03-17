package woowacourse.kanban.board.component.createtaskcard

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import org.junit.Test
import woowacourse.kanban.board.component.testconstan.TestText
import woowacourse.kanban.board.constant.ColorPalette
import woowacourse.kanban.board.constant.HeaderAndFooterConst
import woowacourse.kanban.board.model.Tags
import woowacourse.kanban.board.model.Title

@OptIn(ExperimentalTestApi::class)
class FooterButtonTest {
    @Test
    fun `입력된 제목과 태그가 없으면 생성버튼이 비활성화된다`() = runComposeUiTest {
        val isNotValidTitle = Title(TestText.BLANK_TEXT).isNotValidTitle()
        val isNotValidTags = Tags(TestText.BLANK_TEXT).isNotValidTags()

        setContent {
            FooterButton(
                enabled =  !isNotValidTitle && !isNotValidTags,
                containerColor = ColorPalette.Blue50,
                text = HeaderAndFooterConst.CREATE_BUTTON,
            )
        }

        onNodeWithText(HeaderAndFooterConst.CREATE_BUTTON).assertIsNotEnabled()
    }

    @Test
    fun `제목이 입력되면 생성 버튼이 활성화 된다` () = runComposeUiTest {
        val isNotValidTitle = Title(TestText.TEST_TITLE).isNotValidTitle()
        val isNotValidTags = Tags(TestText.BLANK_TEXT).isNotValidTags()

        setContent {
            FooterButton(
                enabled =  !isNotValidTitle && !isNotValidTags,
                containerColor = ColorPalette.Blue50,
                text = HeaderAndFooterConst.CREATE_BUTTON,
            )
        }

        onNodeWithText(HeaderAndFooterConst.CREATE_BUTTON).assertIsEnabled()
    }

    @Test
    fun `제목이 입력된 상태에서 태그가 5개 이상 입력되면 생성 버튼이 비활성화 된다`() = runComposeUiTest {
        val isNotValidTitle = Title(TestText.TEST_TITLE).isNotValidTitle()
        val isNotValidTags = Tags(TestText.TOO_MANY_TAGS).isNotValidTags()

        setContent {
            FooterButton(
                enabled =  !isNotValidTitle && !isNotValidTags,
                containerColor = ColorPalette.Blue50,
                text = HeaderAndFooterConst.CREATE_BUTTON,
            )
        }

        onNodeWithText(HeaderAndFooterConst.CREATE_BUTTON).assertIsNotEnabled()
    }

    @Test
    fun `제목이 입력된 상태에서 5자 이상인 태그가 입력되면 생성 버튼이 비활성화 된다`() = runComposeUiTest {
        val isNotValidTitle = Title(TestText.TEST_TITLE).isNotValidTitle()
        val isNotValidTags = Tags(TestText.TOO_LONG_TAG).isNotValidTags()

        setContent {
            FooterButton(
                enabled =  !isNotValidTitle && !isNotValidTags,
                containerColor = ColorPalette.Blue50,
                text = HeaderAndFooterConst.CREATE_BUTTON,
            )
        }

        onNodeWithText(HeaderAndFooterConst.CREATE_BUTTON).assertIsNotEnabled()
    }

    @Test
    fun `제목과 태그가 정상적으로 입력되면 생성 버튼이 활성화 된다`() = runComposeUiTest {
        val isNotValidTitle = Title(TestText.TEST_TITLE).isNotValidTitle()
        val isNotValidTags = Tags(TestText.TEST_TAGS).isNotValidTags()

        setContent {
            FooterButton(
                enabled =  !isNotValidTitle && !isNotValidTags,
                containerColor = ColorPalette.Blue50,
                text = HeaderAndFooterConst.CREATE_BUTTON,
            )
        }

        onNodeWithText(HeaderAndFooterConst.CREATE_BUTTON).assertIsEnabled()
    }
}