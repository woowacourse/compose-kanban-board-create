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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.Validator

@Composable
fun Modal() {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var tags by remember { mutableStateOf("") }
    var state by remember { mutableIntStateOf(0) }
    var profile by remember { mutableIntStateOf(0) }

    val isTitleEmpty by remember {
        derivedStateOf {
            Validator.checkTitleIsEmpty(title)
        }
    }

    val isNotValidTag by remember {
        derivedStateOf {
            Validator.checkNotValidTags(tags)
        }
    }

    Card(
        modifier = Modifier
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
                title = title,
                description = description,
                tags = tags,
                onTitleChange = { title = it },
                onDescriptionChange = { description = it },
                onTagsChange = { tags = it },
                isTitleEmpty = isTitleEmpty,
                isNotValidTag = isNotValidTag,
            )
            ButtonSection(
                state = state,
                profile = profile,
                onStateClick = { state = it },
                onProfileClick = { profile = it },
            )
            Footer(
                isButtonEnabled = Validator.checkButtonEnable(isTitleEmpty, isNotValidTag),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ModalPreview() {
    Modal()
}