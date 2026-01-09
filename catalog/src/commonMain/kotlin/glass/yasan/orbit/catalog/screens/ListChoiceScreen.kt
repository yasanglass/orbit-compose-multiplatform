package glass.yasan.orbit.catalog.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import glass.yasan.orbit.catalog.AppTheme
import glass.yasan.orbit.icons.Icons
import glass.yasan.orbit.ui.controls.BadgeCircleInfoSubtle
import glass.yasan.orbit.ui.controls.ButtonPrimarySubtle
import glass.yasan.orbit.ui.controls.Checkbox
import glass.yasan.orbit.ui.controls.Icon
import glass.yasan.orbit.ui.controls.ListChoice
import glass.yasan.orbit.ui.controls.Scaffold
import glass.yasan.orbit.ui.controls.Text
import glass.yasan.orbit.ui.controls.TopAppBar

@Composable
internal fun ListChoiceScreen(
    onNavigateUp: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ListChoice") },
                onNavigateUp = onNavigateUp,
            )
        },
    ) { contentPadding: PaddingValues ->
        Box(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(contentPadding),
        ) {
            ListChoiceScreenInner()
        }
    }
}

@Composable
private fun ListChoiceScreenInner() {
    Column {
        ListChoice(onClick = {}) {
            Text("ListChoice title")
        }
        ListChoice(
            onClick = {},
            description = { Text("Further description") },
        ) {
            Text("ListChoice title")
        }
        ListChoice(
            onClick = {},
            icon = { Icon(Icons.Accommodation, contentDescription = null) },
            trailingIcon = { Icon(Icons.ChevronForward, contentDescription = null) },
        ) {
            Text("ListChoice title")
        }
        ListChoice(
            onClick = {},
            icon = { Icon(Icons.Accommodation, contentDescription = null) },
            trailingIcon = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    BadgeCircleInfoSubtle(value = 1)
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.ChevronForward, contentDescription = null)
                }
            },
            description = { Text("Further description") },
        ) {
            Text("ListChoice title")
        }
        ListChoice(
            onClick = {},
            icon = { Icon(Icons.Bus, contentDescription = null) },
            trailingIcon = {
                ButtonPrimarySubtle(onClick = {}) {
                    Icon(Icons.Plus, contentDescription = null)
                }
            },
        ) {
            Text("ListChoice title")
        }
        ListChoice(
            onClick = {},
            trailingIcon = {
                ButtonPrimarySubtle(onClick = {}) {
                    Icon(Icons.Plus, contentDescription = null)
                }
            },
        ) {
            Text("ListChoice title")
        }
        var checked by rememberSaveable { mutableStateOf(false) }
        ListChoice(
            onClick = { checked = !checked },
            trailingIcon = {
                Checkbox(
                    checked = checked,
                    onCheckedChange = null,
                )
            },
        ) {
            Text("ListChoice title")
        }
        ListChoice(
            description = { Text("This ListChoice is not clickable") },
        ) {
            Text("ListChoice title")
        }
    }
}

@Preview
@Composable
private fun ListChoiceScreenPreview() {
    AppTheme {
        ListChoiceScreen(onNavigateUp = {})
    }
}
