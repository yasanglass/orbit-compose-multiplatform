package glass.yasan.orbit.catalog.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import glass.yasan.orbit.icons.Icons
import glass.yasan.orbit.ui.OrbitTheme
import glass.yasan.orbit.ui.controls.BadgeBundleBasic
import glass.yasan.orbit.ui.controls.BadgeBundleMedium
import glass.yasan.orbit.ui.controls.BadgeBundleTop
import glass.yasan.orbit.ui.controls.BadgeCircleCritical
import glass.yasan.orbit.ui.controls.BadgeCircleCriticalSubtle
import glass.yasan.orbit.ui.controls.BadgeCircleInfo
import glass.yasan.orbit.ui.controls.BadgeCircleInfoSubtle
import glass.yasan.orbit.ui.controls.BadgeCircleNeutral
import glass.yasan.orbit.ui.controls.BadgeCircleNeutralStrong
import glass.yasan.orbit.ui.controls.BadgeCircleNeutralSubtle
import glass.yasan.orbit.ui.controls.BadgeCircleSuccess
import glass.yasan.orbit.ui.controls.BadgeCircleSuccessSubtle
import glass.yasan.orbit.ui.controls.BadgeCircleWarning
import glass.yasan.orbit.ui.controls.BadgeCircleWarningSubtle
import glass.yasan.orbit.ui.controls.BadgeCritical
import glass.yasan.orbit.ui.controls.BadgeCriticalSubtle
import glass.yasan.orbit.ui.controls.BadgeInfo
import glass.yasan.orbit.ui.controls.BadgeInfoSubtle
import glass.yasan.orbit.ui.controls.BadgeNeutral
import glass.yasan.orbit.ui.controls.BadgeNeutralStrong
import glass.yasan.orbit.ui.controls.BadgeNeutralSubtle
import glass.yasan.orbit.ui.controls.BadgePrimitive
import glass.yasan.orbit.ui.controls.BadgeSuccess
import glass.yasan.orbit.ui.controls.BadgeSuccessSubtle
import glass.yasan.orbit.ui.controls.BadgeWarning
import glass.yasan.orbit.ui.controls.BadgeWarningSubtle
import glass.yasan.orbit.ui.controls.Icon
import glass.yasan.orbit.ui.controls.Scaffold
import glass.yasan.orbit.ui.controls.Surface
import glass.yasan.orbit.ui.controls.Text
import glass.yasan.orbit.ui.controls.TopAppBar

@Composable
internal fun BadgeScreen(onNavigateUp: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Badge") },
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
            BadgeScreenInner()
        }
    }
}

@Composable
private fun BadgeScreenInner() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = "Badge",
            style = OrbitTheme.typography.title3,
        )

        Spacer(modifier = Modifier.size(8.dp))

        BadgeRow("BadgeNeutral") {
            BadgeNeutral {
                Text(text = "label")
            }
            BadgeNeutral(
                icon = { Icon(painter = Icons.Airplane, contentDescription = null) },
            ) {
                Text(text = "label")
            }
            BadgeNeutral(
                icon = { Icon(painter = Icons.Airplane, contentDescription = null) },
            ) {
            }
            BadgeCircleNeutral(value = 1, Modifier.alignByBaseline())
        }
        BadgeRow("BadgeNeutralSubtle") {
            BadgeNeutralSubtle {
                Text(text = "label")
            }
            BadgeNeutralSubtle(
                icon = { Icon(painter = Icons.Airplane, contentDescription = null) },
            ) {
                Text(text = "label")
            }
            BadgeNeutralSubtle(
                icon = { Icon(painter = Icons.Airplane, contentDescription = null) },
            ) {
            }
            BadgeCircleNeutralSubtle(value = 1, Modifier.alignByBaseline())
        }
        BadgeRow("BadgeNeutralStrong") {
            BadgeNeutralStrong {
                Text(text = "label")
            }
            BadgeNeutralStrong(
                icon = { Icon(painter = Icons.Airplane, contentDescription = null) },
            ) {
                Text(text = "label")
            }
            BadgeNeutralStrong(
                icon = { Icon(painter = Icons.Airplane, contentDescription = null) },
            ) {
            }
            BadgeCircleNeutralStrong(value = 1, Modifier.alignByBaseline())
        }

        Spacer(modifier = Modifier.size(16.dp))

        BadgeRow("BadgeInfoSubtle") {
            BadgeInfoSubtle {
                Text(text = "label")
            }
            BadgeInfoSubtle(
                icon = { Icon(painter = Icons.InformationCircle, contentDescription = null) },
            ) {
                Text(text = "label")
            }
            BadgeInfoSubtle(
                icon = { Icon(painter = Icons.InformationCircle, contentDescription = null) },
            ) {
            }
            BadgeCircleInfoSubtle(value = 1, Modifier.alignByBaseline())
        }
        BadgeRow("BadgeInfo") {
            BadgeInfo {
                Text(text = "label")
            }
            BadgeInfo(
                icon = { Icon(painter = Icons.InformationCircle, contentDescription = null) },
            ) {
                Text(text = "label")
            }
            BadgeInfo(
                icon = { Icon(painter = Icons.InformationCircle, contentDescription = null) },
            ) {
            }
            BadgeCircleInfo(value = 1, Modifier.alignByBaseline())
        }

        Spacer(modifier = Modifier.size(16.dp))

        BadgeRow("BadgeSuccessSubtle") {
            BadgeSuccessSubtle {
                Text(text = "label")
            }
            BadgeSuccessSubtle(
                icon = { Icon(painter = Icons.Check, contentDescription = null) },
            ) {
                Text(text = "label")
            }
            BadgeSuccessSubtle(
                icon = { Icon(painter = Icons.Check, contentDescription = null) },
            ) {
            }
            BadgeCircleSuccessSubtle(value = 1, Modifier.alignByBaseline())
        }
        BadgeRow("BadgeSuccess") {
            BadgeSuccess {
                Text(text = "label")
            }
            BadgeSuccess(
                icon = { Icon(painter = Icons.Check, contentDescription = null) },
            ) {
                Text(text = "label")
            }
            BadgeSuccess(
                icon = { Icon(painter = Icons.Check, contentDescription = null) },
            ) {
            }
            BadgeCircleSuccess(value = 1, Modifier.alignByBaseline())
        }

        Spacer(modifier = Modifier.size(16.dp))

        BadgeRow("BadgeWarningSubtle") {
            BadgeWarningSubtle {
                Text(text = "label")
            }
            BadgeWarningSubtle(
                icon = { Icon(painter = Icons.Alert, contentDescription = null) },
            ) {
                Text(text = "label")
            }
            BadgeWarningSubtle(
                icon = { Icon(painter = Icons.Alert, contentDescription = null) },
            ) {
            }
            BadgeCircleWarningSubtle(value = 1, Modifier.alignByBaseline())
        }
        BadgeRow("BadgeWarning") {
            BadgeWarning {
                Text(text = "label")
            }
            BadgeWarning(
                icon = { Icon(painter = Icons.Alert, contentDescription = null) },
            ) {
                Text(text = "label")
            }
            BadgeWarning(
                icon = { Icon(painter = Icons.Alert, contentDescription = null) },
            ) {
            }
            BadgeCircleWarning(value = 1, Modifier.alignByBaseline())
        }

        Spacer(modifier = Modifier.size(16.dp))

        BadgeRow("BadgeCriticalSubtle") {
            BadgeCriticalSubtle {
                Text(text = "label")
            }
            BadgeCriticalSubtle(
                icon = { Icon(painter = Icons.Alert, contentDescription = null) },
            ) {
                Text(text = "label")
            }
            BadgeCriticalSubtle(
                icon = { Icon(painter = Icons.Alert, contentDescription = null) },
            ) {
            }
            BadgeCircleCriticalSubtle(value = 1, Modifier.alignByBaseline())
        }
        BadgeRow("BadgeCritical") {
            BadgeCritical {
                Text(text = "label")
            }
            BadgeCritical(
                icon = { Icon(painter = Icons.Alert, contentDescription = null) },
            ) {
                Text(text = "label")
            }
            BadgeCritical(
                icon = { Icon(painter = Icons.Alert, contentDescription = null) },
            ) {
            }
            BadgeCircleCritical(value = 1, Modifier.alignByBaseline())
        }

        Spacer(modifier = Modifier.size(16.dp))

        BadgeRow("BadgeBundleBasic") {
            BadgeBundleBasic {
                Text(text = "label")
            }
            BadgeBundleBasic(
                icon = { Icon(painter = Icons.Alert, contentDescription = null) },
            ) {
                Text(text = "label")
            }
            BadgeBundleBasic(
                icon = { Icon(painter = Icons.Alert, contentDescription = null) },
            ) {
            }
        }
        BadgeRow("BadgeBundleMedium") {
            BadgeBundleMedium {
                Text(text = "label")
            }
            BadgeBundleMedium(
                icon = { Icon(painter = Icons.Alert, contentDescription = null) },
            ) {
                Text(text = "label")
            }
            BadgeBundleMedium(
                icon = { Icon(painter = Icons.Alert, contentDescription = null) },
            ) {
            }
        }
        BadgeRow("BadgeBundleTop") {
            BadgeBundleTop {
                Text(text = "label")
            }
            BadgeBundleTop(
                icon = { Icon(painter = Icons.Alert, contentDescription = null) },
            ) {
                Text(text = "label")
            }
            BadgeBundleTop(
                icon = { Icon(painter = Icons.Alert, contentDescription = null) },
            ) {
            }
        }

        Spacer(modifier = Modifier.size(16.dp))

        BadgeRow("Custom themed") {
            BadgePrimitive(
                backgroundColor = OrbitTheme.colors.info.subtle,
                borderStroke = BorderStroke(0.5.dp, OrbitTheme.colors.info.strong),
                contentColor = OrbitTheme.colors.content.normal,
                icon = { Icon(painter = Icons.Close, contentDescription = null) },
            ) {
                Text(text = "Custom badge")
            }
        }

        Spacer(modifier = Modifier.size(16.dp))

        Text(
            text = "BadgeCircle",
            style = OrbitTheme.typography.title3,
        )

        Spacer(modifier = Modifier.size(8.dp))

        BadgeRow(name = "BadgeCircleNeutral") {
            BadgeCircleNeutral(1)
            BadgeCircleNeutral(22)
            BadgeCircleNeutral(333, Modifier.alignByBaseline())
        }

        BadgeRow(name = "BadgeCircleNeutralSubtle") {
            BadgeCircleNeutralSubtle(1)
            BadgeCircleNeutralSubtle(22)
            BadgeCircleNeutralSubtle(333, Modifier.alignByBaseline())
        }

        BadgeRow(name = "BadgeCircleNeutralStrong") {
            BadgeCircleNeutralStrong(1)
            BadgeCircleNeutralStrong(22)
            BadgeCircleNeutralStrong(333, Modifier.alignByBaseline())
        }

        Spacer(modifier = Modifier.size(16.dp))

        BadgeRow(name = "BadgeCircleInfoSubtle") {
            BadgeCircleInfoSubtle(1)
            BadgeCircleInfoSubtle(22)
            BadgeCircleInfoSubtle(333, Modifier.alignByBaseline())
        }

        BadgeRow(name = "BadgeCircleInfo") {
            BadgeCircleInfo(1)
            BadgeCircleInfo(22)
            BadgeCircleInfo(333, Modifier.alignByBaseline())
        }

        Spacer(modifier = Modifier.size(16.dp))

        BadgeRow(name = "BadgeCircleSuccessSubtle") {
            BadgeCircleSuccessSubtle(1)
            BadgeCircleSuccessSubtle(22)
            BadgeCircleSuccessSubtle(333, Modifier.alignByBaseline())
        }

        BadgeRow(name = "BadgeCircleSuccess") {
            BadgeCircleSuccess(1)
            BadgeCircleSuccess(22)
            BadgeCircleSuccess(333, Modifier.alignByBaseline())
        }

        Spacer(modifier = Modifier.size(16.dp))

        BadgeRow(name = "BadgeCircleWarningSubtle") {
            BadgeCircleWarningSubtle(1)
            BadgeCircleWarningSubtle(22)
            BadgeCircleWarningSubtle(333, Modifier.alignByBaseline())
        }

        BadgeRow(name = "BadgeCircleWarning") {
            BadgeCircleWarning(1)
            BadgeCircleWarning(22)
            BadgeCircleWarning(333, Modifier.alignByBaseline())
        }

        Spacer(modifier = Modifier.size(16.dp))

        BadgeRow(name = "BadgeCircleCriticalSubtle") {
            BadgeCircleCriticalSubtle(1)
            BadgeCircleCriticalSubtle(22)
            BadgeCircleCriticalSubtle(333, Modifier.alignByBaseline())
        }

        BadgeRow(name = "BadgeCircleCritical") {
            BadgeCircleCritical(1)
            BadgeCircleCritical(22)
            BadgeCircleCritical(333, Modifier.alignByBaseline())
        }
    }
}

@Composable
private fun BadgeRow(
    name: String,
    content: @Composable RowScope.() -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        content()
        Text(
            text = name,
            modifier = Modifier
                .padding(start = 8.dp)
                .alignByBaseline(),
            style = OrbitTheme.typography.bodyNormalMedium,
        )
    }
}

@Preview
@Composable
private fun BadgeScreenPreview() {
    Surface {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            BadgeScreenInner()
        }
    }
}
