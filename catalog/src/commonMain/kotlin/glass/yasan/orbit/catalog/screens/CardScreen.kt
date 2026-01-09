package glass.yasan.orbit.catalog.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import glass.yasan.orbit.catalog.AppTheme
import glass.yasan.orbit.catalog.components.CustomPlaceholder
import glass.yasan.orbit.icons.Icons
import glass.yasan.orbit.ui.OrbitTheme
import glass.yasan.orbit.ui.controls.ButtonTextLinkPrimary
import glass.yasan.orbit.ui.controls.Card
import glass.yasan.orbit.ui.controls.Icon
import glass.yasan.orbit.ui.controls.ListChoice
import glass.yasan.orbit.ui.controls.Scaffold
import glass.yasan.orbit.ui.controls.Text
import glass.yasan.orbit.ui.controls.TopAppBar

@Composable
internal fun CardScreen(
    onNavigateUp: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Card") },
                onNavigateUp = onNavigateUp,
            )
        },
        backgroundColor = OrbitTheme.colors.surface.subtle,
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(contentPadding),
        ) {
            CardScreenInner()
        }
    }
}

@Composable
private fun CardScreenInner() {
    Column(
        modifier = Modifier.padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Card(
            title = { Text("Card title with many words and even longer") },
            action = { ButtonTextLinkPrimary("This is a longer call to action", onClick = {}) },
            description = { Text("Description should contain short and relevant information.") },
        ) {
            CustomPlaceholder()
        }
        Card(
            title = { Text("Card title") },
            action = { ButtonTextLinkPrimary("Action", onClick = {}) },
        ) {
            CustomPlaceholder()
        }
        Card(
            title = { Text("Card title", style = OrbitTheme.typography.title1) },
            action = { ButtonTextLinkPrimary("Action", onClick = {}) },
        ) {
            CustomPlaceholder()
        }
        Card(
            title = { Text("Card title") },
            contentPadding = PaddingValues(vertical = 12.dp),
        ) {
            Column {
                ListChoice(
                    onClick = {},
                    icon = { Icon(Icons.AirplaneTakeoff, contentDescription = null) },
                    trailingIcon = { Icon(Icons.ChevronForward, contentDescription = null) },
                ) {
                    Text("Takeoff")
                }
                ListChoice(
                    onClick = {},
                    icon = { Icon(Icons.AirplaneLanding, contentDescription = null) },
                    trailingIcon = { Icon(Icons.ChevronForward, contentDescription = null) },
                ) {
                    Text("Landing")
                }
            }
        }
    }
}

@Preview
@Composable
private fun CardScreenPreview() {
    AppTheme {
        CardScreen(onNavigateUp = {})
    }
}
