package glass.yasan.orbit.catalog.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import org.jetbrains.compose.ui.tooling.preview.Preview
import glass.yasan.orbit.ui.controls.Scaffold
import glass.yasan.orbit.ui.controls.Tab
import glass.yasan.orbit.ui.controls.TabRow
import glass.yasan.orbit.ui.controls.Text
import glass.yasan.orbit.ui.controls.TopAppBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun TabsScreen(onNavigateUp: () -> Unit) {
    val pagerState = rememberPagerState(0) { 3 }
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tabs") },
                onNavigateUp = onNavigateUp,
                extraContent = {
                    TabRow(selectedTabIndex = pagerState.currentPage) {
                        Tab(
                            selected = pagerState.currentPage == 0,
                            onClick = { scope.launch { pagerState.animateScrollToPage(0) } },
                            text = { Text("Variant A") },
                        )
                        Tab(
                            selected = pagerState.currentPage == 1,
                            onClick = { scope.launch { pagerState.animateScrollToPage(1) } },
                            text = { Text("Variant B") },
                        )
                        Tab(
                            selected = pagerState.currentPage == 2,
                            onClick = { scope.launch { pagerState.animateScrollToPage(2) } },
                            text = { Text("Variant C") },
                        )
                    }
                },
            )
        },
    ) { contentPadding: PaddingValues ->
        HorizontalPager(
            state = pagerState,
        ) {
            when (it) {
                0 -> CustomContentPlaceholder(contentPadding, text = "Variant A")
                1 -> CustomContentPlaceholder(contentPadding, text = "Variant B")
                2 -> CustomContentPlaceholder(contentPadding, text = "Variant C")
            }
        }
    }
}

@Preview
@Composable
private fun TagScreenPreview() {
    TabsScreen({})
}
