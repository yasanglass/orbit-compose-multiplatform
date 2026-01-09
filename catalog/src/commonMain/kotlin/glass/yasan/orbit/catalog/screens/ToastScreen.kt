package glass.yasan.orbit.catalog.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import glass.yasan.orbit.icons.IconName
import glass.yasan.orbit.ui.controls.ButtonSecondary
import glass.yasan.orbit.ui.controls.Scaffold
import glass.yasan.orbit.ui.controls.Text
import glass.yasan.orbit.ui.controls.ToastHostState
import glass.yasan.orbit.ui.controls.TopAppBar
import glass.yasan.orbit.ui.controls.rememberToastHostState
import kotlin.time.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
internal fun ToastScreen(
    onNavigateUp: () -> Unit,
) {
    var dismissedText by remember { mutableStateOf("") }
    val toastHostState = rememberToastHostState(
        onDismiss = {
            val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            dismissedText = "Last toast dismissed at ${now.hour}:${now.minute}:${now.second}"
        },
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Toast") },
                onNavigateUp = onNavigateUp,
            )
        },
        toastHostState = toastHostState,
    ) { contentPadding: PaddingValues ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(contentPadding),
        ) {
            ToastScreenInner(dismissedText, toastHostState)
        }
    }
}

@Composable
private fun ToastScreenInner(
    dismissedText: String,
    toastHostState: ToastHostState,
) {
    Column(
        Modifier
            .fillMaxSize()
            .wrapContentHeight()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ButtonSecondary(
            onClick = {
                toastHostState.showToast("You’re signed in as jon.snow@wall.7k.", IconName.CheckCircle)
            },
            content = { Text("Toast – signed in") },
        )
        ButtonSecondary(
            onClick = {
                toastHostState.showToast("We’ll notify you when the price changes.", IconName.Notification)
            },
            content = { Text("Toast – price alert created") },
        )
        ButtonSecondary(
            onClick = {
                toastHostState.showToast(
                    "On mobile there’s always a fixed width to make the Toast stand out a bit more.",
                )
            },
            content = { Text("Toast – long message") },
        )
        ButtonSecondary(
            onClick = {
                toastHostState.showToast(
                    message = "Added to New York trip",
                    iconName = IconName.Heart,
                    actionLabel = "Change",
                )
            },
            content = { Text("Toast – add to trip with icon") },
        )
        ButtonSecondary(
            onClick = {
                toastHostState.showToast(
                    message = "Added to New York trip",
                    imageUrl = "https://images.kiwi.com/photos/600x330/new-york-city_ny_us.webp",
                    actionLabel = "Change",
                )
            },
            content = { Text("Toast – add to trip with image") },
        )
        Text(
            text = dismissedText,
            modifier = Modifier.padding(top = 24.dp),
        )
    }
}
