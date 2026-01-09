package glass.yasan.orbit.catalog.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import glass.yasan.orbit.catalog.components.CustomPlaceholder
import glass.yasan.orbit.icons.Icons
import glass.yasan.orbit.ui.OrbitTheme
import glass.yasan.orbit.ui.controls.Icon
import glass.yasan.orbit.ui.controls.IconButton
import glass.yasan.orbit.ui.controls.Scaffold
import glass.yasan.orbit.ui.controls.Tab
import glass.yasan.orbit.ui.controls.TabRow
import glass.yasan.orbit.ui.controls.Tag
import glass.yasan.orbit.ui.controls.Text
import glass.yasan.orbit.ui.controls.TopAppBar
import glass.yasan.orbit.ui.controls.TopAppBarScrollBehavior

@Composable
internal fun TopAppBarNormalScreen(
    onNavigateUp: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Simple") },
                onNavigateUp = onNavigateUp,
                actions = {
                    IconButton(onClick = {}) {
                        Icon(painter = Icons.Notification, contentDescription = null)
                    }
                },
            )
        },
        content = { CustomContentPlaceholder(it) },
    )
}

@Composable
internal fun TopAppBarNormalScrollableScreen(
    onNavigateUp: () -> Unit,
) {
    val scrollBehavior = TopAppBarScrollBehavior.enterAlways()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text("Scrollable") },
                onNavigateUp = onNavigateUp,
                scrollBehavior = scrollBehavior,
            )
        },
        content = { CustomContentPlaceholder(it) },
    )
}

@Composable
internal fun TopAppBarNormalWithTabsScreen(
    onNavigateUp: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("With Tabs") },
                onNavigateUp = onNavigateUp,
                extraContent = {
                    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
                    TabRow(selectedTabIndex = selectedTabIndex) {
                        Tab(
                            selected = selectedTabIndex == 0,
                            onClick = { selectedTabIndex = 0 },
                            text = { Text("Normal") },
                        )
                        Tab(
                            selected = selectedTabIndex == 1,
                            onClick = { selectedTabIndex = 1 },
                            text = { Text("Inline") },
                        )
                    }
                },
            )
        },
        content = { CustomContentPlaceholder(it) },
    )
}

@Composable
internal fun TopAppBarNormalWithFiltersScreen(
    onNavigateUp: () -> Unit,
) {
    val scrollBehavior = TopAppBarScrollBehavior.pinned()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text("With Filters") },
                onNavigateUp = onNavigateUp,
                extraContent = {
                    Row(
                        Modifier
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 8.dp),
                    ) {
                        var selected by rememberSaveable { mutableStateOf(true) }
                        Tag(selected = selected, onSelect = { selected = !selected }) {
                            Text("Custom Filter")
                        }
                    }
                },
            )
        },
        content = { CustomContentPlaceholder(it) },
    )
}

@Composable
internal fun CustomContentPlaceholder(
    contentPadding: PaddingValues,
    text: String = "Custom Content",
) {
    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .padding(contentPadding)
            .padding(16.dp),
    ) {
        CustomPlaceholder(
            Modifier
                .height(987.dp)
                .clip(OrbitTheme.shapes.large),
            text,
        )
    }
}
