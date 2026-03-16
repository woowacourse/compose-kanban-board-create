package woowacourse.kanban.board.component.createtaskcard

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test
import woowacourse.kanban.board.constant.Blue50
import woowacourse.kanban.board.constant.HeaderAndFooterConst

@OptIn(ExperimentalTestApi::class)
class FooterTest {

    @Test
    fun `제목이 입력되면 생성 버튼이 활성화된다`() = runComposeUiTest {
        val title = "제목"
        val isTitleEmpty = Validator.checkTitleIsEmpty(title)
        val tags = ""
        val isNotValidTag = Validator.checkNotValidTags(tags)
        val isButtonEnabled = Validator.checkButtonEnable(isTitleEmpty, isNotValidTag)

        setContent {
            FooterButton(
                enabled = isButtonEnabled,
                containerColor = Blue50,
                text = HeaderAndFooterConst.CREATE_BUTTON,
            )
        }
        onNodeWithText(HeaderAndFooterConst.CREATE_BUTTON)
            .assertIsEnabled()
    }

    @Test
    fun `제목이 입력되지 않으면 생성 버튼이 비활성화된다`() = runComposeUiTest {
        val title = ""
        val isTitleEmpty = Validator.checkTitleIsEmpty(title)
        val tags = ""
        val isNotValidTag = Validator.checkNotValidTags(tags)
        val isButtonEnabled = Validator.checkButtonEnable(isTitleEmpty, isNotValidTag)

        setContent {
            FooterButton(
                enabled = isButtonEnabled,
                containerColor = Blue50,
                text = HeaderAndFooterConst.CREATE_BUTTON,
            )
        }
        onNodeWithText(HeaderAndFooterConst.CREATE_BUTTON)
            .assertIsNotEnabled()
    }

    @Test
    fun `제목이 입력되고 태그가 5개 이상 입력되면 생성 버튼이 비활성화된다`() = runComposeUiTest {
        val title = "제목"
        val isTitleEmpty = Validator.checkTitleIsEmpty(title)
        val tags = "1,2,3,4,5,6"
        val isNotValidTag = Validator.checkNotValidTags(tags)
        val isButtonEnabled = Validator.checkButtonEnable(isTitleEmpty, isNotValidTag)

        setContent {
            FooterButton(
                enabled = isButtonEnabled,
                containerColor = Blue50,
                text = HeaderAndFooterConst.CREATE_BUTTON,
            )
        }
        onNodeWithText(HeaderAndFooterConst.CREATE_BUTTON)
            .assertIsNotEnabled()
    }

    @Test
    fun `제목이 입력되고 5글자 초과인 태그가 입력되면 생성 버튼이 비활성화된다`() = runComposeUiTest {
        val title = "제목"
        val isTitleEmpty = Validator.checkTitleIsEmpty(title)
        val tags = "1,2,3,1234567899"
        val isNotValidTag = Validator.checkNotValidTags(tags)
        val isButtonEnabled = Validator.checkButtonEnable(isTitleEmpty, isNotValidTag)

        setContent {
            FooterButton(
                enabled = isButtonEnabled,
                containerColor = Blue50,
                text = HeaderAndFooterConst.CREATE_BUTTON,
            )
        }
        onNodeWithText(HeaderAndFooterConst.CREATE_BUTTON)
            .assertIsNotEnabled()
    }

    @Test
    fun `제목이 입력되고 5글자 이하인 태그가 5개 이하로 입력되면 생성 버튼이 활성화된다`() = runComposeUiTest {
        val title = "제목"
        val isTitleEmpty = Validator.checkTitleIsEmpty(title)
        val tags = "1,2,3,123"
        val isNotValidTag = Validator.checkNotValidTags(tags)
        val isButtonEnabled = Validator.checkButtonEnable(isTitleEmpty, isNotValidTag)

        setContent {
            FooterButton(
                enabled = isButtonEnabled,
                containerColor = Blue50,
                text = HeaderAndFooterConst.CREATE_BUTTON,
            )
        }
        onNodeWithText(HeaderAndFooterConst.CREATE_BUTTON)
            .assertIsEnabled()
    }
}
