package glass.yasan.orbit.catalog.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import glass.yasan.orbit.catalog.AppTheme
import glass.yasan.orbit.icons.Icons
import glass.yasan.orbit.ui.OrbitTheme
import glass.yasan.orbit.ui.controls.ButtonBundleBasic
import glass.yasan.orbit.ui.controls.ButtonBundleMedium
import glass.yasan.orbit.ui.controls.ButtonBundleTop
import glass.yasan.orbit.ui.controls.ButtonCritical
import glass.yasan.orbit.ui.controls.ButtonCriticalSubtle
import glass.yasan.orbit.ui.controls.ButtonLinkCritical
import glass.yasan.orbit.ui.controls.ButtonLinkPrimary
import glass.yasan.orbit.ui.controls.ButtonLinkSecondary
import glass.yasan.orbit.ui.controls.ButtonPrimary
import glass.yasan.orbit.ui.controls.ButtonPrimarySubtle
import glass.yasan.orbit.ui.controls.ButtonPrimitive
import glass.yasan.orbit.ui.controls.ButtonSecondary
import glass.yasan.orbit.ui.controls.ButtonTextLinkCritical
import glass.yasan.orbit.ui.controls.ButtonTextLinkPrimary
import glass.yasan.orbit.ui.controls.ButtonToggleContainer
import glass.yasan.orbit.ui.controls.Icon
import glass.yasan.orbit.ui.controls.Scaffold
import glass.yasan.orbit.ui.controls.Surface
import glass.yasan.orbit.ui.controls.SurfaceCard
import glass.yasan.orbit.ui.controls.Tab
import glass.yasan.orbit.ui.controls.TabRow
import glass.yasan.orbit.ui.controls.Text
import glass.yasan.orbit.ui.controls.TopAppBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ButtonScreen(onNavigateUp: () -> Unit) {
    val state = rememberPagerState(0) { 2 }
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Buttons") },
                onNavigateUp = onNavigateUp,
                extraContent = {
                    TabRow(selectedTabIndex = state.currentPage) {
                        Tab(
                            selected = state.currentPage == 0,
                            onClick = { scope.launch { state.animateScrollToPage(0) } },
                            text = { Text("Button") },
                        )
                        Tab(
                            selected = state.currentPage == 1,
                            onClick = { scope.launch { state.animateScrollToPage(1) } },
                            text = { Text("ButtonLink") },
                        )
                    }
                },
            )
        },
    ) { contentPadding ->
        HorizontalPager(
            state = state,
            modifier = Modifier.padding(top = contentPadding.calculateTopPadding()),
        ) { tabIndex ->
            Box(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(
                        start = contentPadding.calculateStartPadding(LayoutDirection.Ltr),
                        end = contentPadding.calculateEndPadding(LayoutDirection.Ltr),
                        bottom = contentPadding.calculateBottomPadding(),
                    ),
            ) {
                when (tabIndex) {
                    0 -> ButtonScreenInner()
                    1 -> ButtonLinkScreenInner()
                }
            }
        }
    }
}

@Composable
private fun ButtonScreenInner() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(16.dp),
    ) {
        val maxWidth = Modifier.fillMaxWidth()
        var variant by rememberSaveable { mutableIntStateOf(0) }
        val onClick = { variant = (variant + 1).mod(4) }

        ButtonPrimary(onClick = onClick, maxWidth) { Inner(variant, "Primary Button") }
        ButtonPrimarySubtle(onClick = onClick, maxWidth) { Inner(variant, "Primary Subtle Button") }
        ButtonSecondary(onClick = onClick, maxWidth) { Inner(variant, "Secondary Button") }
        ButtonCritical(onClick = onClick, maxWidth) { Inner(variant, "Critical Button") }
        ButtonCriticalSubtle(onClick = onClick, maxWidth) { Inner(variant, "Critical Subtle Button") }
        ButtonBundleBasic(onClick = onClick, maxWidth) { Inner(variant, "Bundle Basic Button") }
        ButtonBundleMedium(onClick = onClick, maxWidth) { Inner(variant, "Bundle Medium Button") }
        ButtonBundleTop(onClick = onClick, maxWidth) { Inner(variant, "Bundle Top Button") }

        Text("Manually themed", Modifier.padding(top = 16.dp))

        ButtonPrimitive(
            onClick = onClick,
            modifier = maxWidth,
            backgroundColor = OrbitTheme.colors.info.normal,
        ) {
            Inner(variant, "Info Button")
        }

        Text("Button Toggling", Modifier.padding(top = 16.dp))
        var targetState by rememberSaveable { mutableStateOf(true) }
        ButtonToggleContainer(targetState) { state ->
            if (state) {
                ButtonPrimarySubtle(onClick = { targetState = !targetState }, maxWidth) {
                    Inner(variant, "Add Service")
                }
            } else {
                ButtonSecondary(onClick = { targetState = !targetState }, maxWidth) {
                    Inner(variant, "Remove Service")
                }
            }
        }
    }
}

@Composable
private fun RowScope.Inner(variant: Int, label: String) {
    when (variant) {
        0 -> {
            Text(label)
        }
        1 -> {
            Icon(Icons.Android, contentDescription = null)
            Text(label)
        }
        2 -> {
            Icon(Icons.Android, contentDescription = null)
        }
        3 -> {
            Text(label, Modifier.weight(1f), textAlign = TextAlign.Start)
            Icon(Icons.ChevronForward, contentDescription = null)
        }
    }
}

@Composable
private fun ButtonLinkScreenInner() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(16.dp),
    ) {
        ButtonLinkPrimary(onClick = {}, Modifier.fillMaxWidth()) { Text("Primary ButtonLink") }
        ButtonLinkSecondary(onClick = {}, Modifier.fillMaxWidth()) { Text("Secondary ButtonLink") }
        ButtonLinkCritical(onClick = {}, Modifier.fillMaxWidth()) { Text("Critical ButtonLink") }

        SurfaceCard {
            Column(Modifier.padding(16.dp)) {
                Text(
                    text = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Nullam sit amet magna " +
                        "in magna gravida vehicula. Nam quis nulla. Nam sed tellus id magna elementum tincidunt.",
                )
                Spacer(Modifier.size(8.dp))
                ButtonTextLinkPrimary("Translate", onClick = {})
            }
        }

        SurfaceCard {
            Row(Modifier.padding(16.dp)) {
                Text(
                    text = "Title",
                    style = OrbitTheme.typography.title3,
                    modifier = Modifier
                        .alignByBaseline()
                        .weight(1f),
                )
                Spacer(Modifier.size(8.dp))
                ButtonTextLinkCritical("Delete", onClick = {}, modifier = Modifier.alignByBaseline())
            }
        }
    }
}

@Preview
@Composable
private fun ButtonScreenPreview() {
    AppTheme {
        Surface {
            ButtonScreenInner()
        }
    }
}
