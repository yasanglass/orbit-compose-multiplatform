package glass.yasan.orbit.catalog

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import glass.yasan.orbit.catalog.screens.AlertScreen
import glass.yasan.orbit.catalog.screens.BadgeListScreen
import glass.yasan.orbit.catalog.screens.BadgeScreen
import glass.yasan.orbit.catalog.screens.ButtonScreen
import glass.yasan.orbit.catalog.screens.CardScreen
import glass.yasan.orbit.catalog.screens.CheckboxScreen
import glass.yasan.orbit.catalog.screens.ChoiceTileScreen
import glass.yasan.orbit.catalog.screens.CollapseScreen
import glass.yasan.orbit.catalog.screens.ColorsScreen
import glass.yasan.orbit.catalog.screens.CouponScreen
import glass.yasan.orbit.catalog.screens.DialogsMaterialDialog
import glass.yasan.orbit.catalog.screens.DialogsOrbitDialog
import glass.yasan.orbit.catalog.screens.DialogsScreen
import glass.yasan.orbit.catalog.screens.EmptyStateScreen
import glass.yasan.orbit.catalog.screens.IconsScreen
import glass.yasan.orbit.catalog.screens.IllustrationsScreen
import glass.yasan.orbit.catalog.screens.KeyValueScreen
import glass.yasan.orbit.catalog.screens.LinearProgressIndicatorScreen
import glass.yasan.orbit.catalog.screens.ListChoiceScreen
import glass.yasan.orbit.catalog.screens.ListScreen
import glass.yasan.orbit.catalog.screens.LoadingScreen
import glass.yasan.orbit.catalog.screens.MainScreen
import glass.yasan.orbit.catalog.screens.PillButtonScreen
import glass.yasan.orbit.catalog.screens.RadioScreen
import glass.yasan.orbit.catalog.screens.SeatScreen
import glass.yasan.orbit.catalog.screens.SegmentedSwitchScreen
import glass.yasan.orbit.catalog.screens.SelectFieldScreen
import glass.yasan.orbit.catalog.screens.SliderScreen
import glass.yasan.orbit.catalog.screens.StepperScreen
import glass.yasan.orbit.catalog.screens.SurfaceCardScreen
import glass.yasan.orbit.catalog.screens.SwitchScreen
import glass.yasan.orbit.catalog.screens.TabsScreen
import glass.yasan.orbit.catalog.screens.TagScreen
import glass.yasan.orbit.catalog.screens.TextFieldScreen
import glass.yasan.orbit.catalog.screens.TileGroupScreen
import glass.yasan.orbit.catalog.screens.TileScreen
import glass.yasan.orbit.catalog.screens.TimelineScreen
import glass.yasan.orbit.catalog.screens.ToastScreen
import glass.yasan.orbit.catalog.screens.TypographyScreen
import glass.yasan.orbit.catalog.screens.topAppBarNavigation
import kotlinx.serialization.ExperimentalSerializationApi

@Composable
fun App() {
    var isLightThemeUser by rememberSaveable { mutableStateOf<Boolean?>(null) }
    val isLightThemeFinal = isLightThemeUser ?: !isSystemInDarkTheme()

    AnimatedAppTheme(
        isLightTheme = isLightThemeFinal,
        onThemeToggle = { isLight ->
            isLightThemeUser = isLight
        },
    ) { onThemeToggle ->
        NavGraph(onThemeToggle = onThemeToggle)
    }
}

@OptIn(ExperimentalSerializationApi::class)
@Composable
private fun NavGraph(
    onThemeToggle: (offset: Offset) -> Unit,
) {
    val density = LocalDensity.current
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destinations.Main,
        enterTransition = { SharedXAxisEnterTransition(density) },
        exitTransition = { SharedXAxisExitTransition(density) },
        popEnterTransition = { SharedXAxisPopEnterTransition(density) },
        popExitTransition = { SharedXAxisPopExitTransition(density) },
    ) {
        composable<Destinations.Main> { MainScreen(navController::navigate, onThemeToggle) }

        composable<Destinations.Colors> { ColorsScreen(navController::navigateUp) }
        composable<Destinations.Icons> { IconsScreen(navController::navigateUp) }
        composable<Destinations.Illustrations> { IllustrationsScreen(navController::navigateUp) }
        composable<Destinations.Typography> { TypographyScreen(navController::navigateUp) }

        composable<Destinations.Alert> { AlertScreen(navController::navigateUp) }
        composable<Destinations.Badge> { BadgeScreen(navController::navigateUp) }
        composable<Destinations.BadgeList> { BadgeListScreen(navController::navigateUp) }
        composable<Destinations.Button> { ButtonScreen(navController::navigateUp) }
        composable<Destinations.Card> { CardScreen(navController::navigateUp) }
        composable<Destinations.Checkbox> { CheckboxScreen(navController::navigateUp) }
        composable<Destinations.ChoiceTile> { ChoiceTileScreen(navController::navigateUp) }
        composable<Destinations.Collapse> { CollapseScreen(navController::navigateUp) }
        composable<Destinations.Coupon> { CouponScreen(navController::navigateUp) }
        composable<Destinations.Dialog> { DialogsScreen(navController) }
        dialog<Destinations.DialogMaterial> { DialogsMaterialDialog(navController) }
        dialog<Destinations.DialogOrbit> { DialogsOrbitDialog(navController) }
        composable<Destinations.EmptyState> { EmptyStateScreen(navController::navigateUp) }
        composable<Destinations.KeyValue> { KeyValueScreen(navController::navigateUp) }
        composable<Destinations.LinearProgressIndicator> { LinearProgressIndicatorScreen(navController::navigateUp) }
        composable<Destinations.List> { ListScreen(navController::navigateUp) }
        composable<Destinations.ListChoice> { ListChoiceScreen(navController::navigateUp) }
        composable<Destinations.Loading> { LoadingScreen(navController::navigateUp) }
        composable<Destinations.Radio> { RadioScreen(navController::navigateUp) }
        composable<Destinations.PillButton> { PillButtonScreen(navController::navigateUp) }
        composable<Destinations.Seat> { SeatScreen(navController::navigateUp) }
        composable<Destinations.SegmentedSwitch> { SegmentedSwitchScreen(navController::navigateUp) }
        composable<Destinations.SelectField> { SelectFieldScreen(navController::navigateUp) }
        composable<Destinations.Slider> { SliderScreen(navController::navigateUp) }
        composable<Destinations.Stepper> { StepperScreen(navController::navigateUp) }
        composable<Destinations.SurfaceCard> { SurfaceCardScreen(navController::navigateUp) }
        composable<Destinations.Switch> { SwitchScreen(navController::navigateUp) }
        composable<Destinations.Tabs> { TabsScreen(navController::navigateUp) }
        composable<Destinations.Tag> { TagScreen(navController::navigateUp) }
        composable<Destinations.TextField> { TextFieldScreen(navController::navigateUp) }
        composable<Destinations.Tile> { TileScreen(navController::navigateUp) }
        composable<Destinations.TileGroup> { TileGroupScreen(navController::navigateUp) }
        composable<Destinations.Timeline> { TimelineScreen(navController::navigateUp) }
        composable<Destinations.Toast> { ToastScreen(navController::navigateUp) }
        topAppBarNavigation<Destinations.TopAppBar>(navController)
    }
}

private val SharedXAxisEnterTransition: (Density) -> EnterTransition = { density ->
    fadeIn(animationSpec = tween(durationMillis = 210, delayMillis = 90, easing = LinearOutSlowInEasing)) +
        slideInHorizontally(animationSpec = tween(durationMillis = 300)) {
            with(density) { 30.dp.roundToPx() }
        }
}

private val SharedXAxisPopEnterTransition: (Density) -> EnterTransition = { density ->
    fadeIn(animationSpec = tween(durationMillis = 210, delayMillis = 90, easing = LinearOutSlowInEasing)) +
        slideInHorizontally(animationSpec = tween(durationMillis = 300)) {
            with(density) { (-30).dp.roundToPx() }
        }
}

private val SharedXAxisExitTransition: (Density) -> ExitTransition = { density ->
    fadeOut(animationSpec = tween(durationMillis = 90, easing = FastOutLinearInEasing)) +
        slideOutHorizontally(animationSpec = tween(durationMillis = 300)) {
            with(density) { (-30).dp.roundToPx() }
        }
}

private val SharedXAxisPopExitTransition: (Density) -> ExitTransition = { density ->
    fadeOut(animationSpec = tween(durationMillis = 90, easing = FastOutLinearInEasing)) +
        slideOutHorizontally(animationSpec = tween(durationMillis = 300)) {
            with(density) { 30.dp.roundToPx() }
        }
}
