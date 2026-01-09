package glass.yasan.orbit.catalog.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import glass.yasan.orbit.catalog.AppTheme
import glass.yasan.orbit.catalog.components.CustomPlaceholder
import glass.yasan.orbit.icons.Icons
import glass.yasan.orbit.ui.OrbitTheme
import glass.yasan.orbit.ui.controls.Icon
import glass.yasan.orbit.ui.controls.Scaffold
import glass.yasan.orbit.ui.controls.Text
import glass.yasan.orbit.ui.controls.Tile
import glass.yasan.orbit.ui.controls.TileGroup
import glass.yasan.orbit.ui.controls.TopAppBar

@Composable
internal fun TileGroupScreen(
    onNavigateUp: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("TileGroup") },
                onNavigateUp = onNavigateUp,
            )
        },
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(contentPadding),
        ) {
            TileGroupScreenInner()
        }
    }
}

@Composable
private fun TileGroupScreenInner() {
    TileGroup(
        modifier = Modifier.padding(16.dp),
    ) {
        Tile(
            title = { Text("Title") },
            onClick = {},
        )
        Tile(
            title = { Text("Title") },
            onClick = {},
            icon = { Icon(Icons.Airplane, contentDescription = null) },
        )
        Tile(
            title = { Text("Title") },
            onClick = {},
            description = { Text("Description") },
        )
        Tile(
            title = { Text("Title") },
            onClick = {},
            icon = { Icon(Icons.Airplane, contentDescription = null) },
            description = { Text("Description") },
        )
        Tile(
            title = { Text("Title") },
            onClick = {},
            description = { Text("Description") },
            trailingContent = {
                Text(
                    text = "Action",
                    modifier = Modifier.align(Alignment.Top),
                    color = OrbitTheme.colors.primary.normal,
                    style = OrbitTheme.typography.bodyNormalMedium,
                )
            },
        )
        Tile(
            onClick = {},
        ) {
            CustomPlaceholder()
        }
    }
}

@Preview
@Composable
private fun TileGroupScreenPreview() {
    AppTheme {
        TileGroupScreen(onNavigateUp = {})
    }
}
