package woowacourse.kanban.board.component.modal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.model.ProfileState
import woowacourse.kanban.board.model.TaskState
import woowacourse.kanban.board.model.TextInputState
import woowacourse.kanban.board.model.modal.Tags
import woowacourse.kanban.board.model.modal.Title

@Composable
fun Modal(modifier: Modifier = Modifier) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var tags by remember { mutableStateOf("") }
    var taskState by remember { mutableStateOf(TaskState.TODO) }
    var profileState by remember { mutableStateOf(ProfileState.DINO) }

    val isTitleValid by remember {
        derivedStateOf {
            Title.isTitleValid(title)
        }
    }

    val isTagsValid by remember {
        derivedStateOf {
            Tags.isTagsValid(tags)
        }
    }

    val titleInputState = TextInputState(
        value = title,
        onChange = { title = it },
        isError = isTitleValid.not(),
    )

    val descriptionInputState = TextInputState(
        value = description,
        onChange = { description = it },
    )

    val tagsInputState = TextInputState(
        value = tags,
        onChange = { tags = it },
        isError = isTagsValid.not(),
    )

    Card(
        modifier = modifier
            .width(800.dp)
            .padding(50.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            Header()
            HorizontalDivider()
            TextInputSection(
                titleInputState = titleInputState,
                descriptionInputState = descriptionInputState,
                tagsInputState = tagsInputState,
            )
            ButtonSection(
                state = taskState,
                profileState = profileState,
                onStateClick = { taskState = it },
                onProfileClick = { profileState = it },
            )
            Footer(
                isButtonEnabled = isTitleValid && isTagsValid,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ModalPreview() {
    Modal()
}
