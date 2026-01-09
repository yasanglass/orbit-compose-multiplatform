package glass.yasan.orbit.ui.controls.internal

import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import glass.yasan.orbit.ui.OrbitTheme

@Composable
internal fun OrbitElevations(
    neutralize: Dp = 0.dp,
    content: @Composable () -> Unit,
) {
    val isLight = OrbitTheme.colors.isLight
    CompositionLocalProvider(
        LocalAbsoluteTonalElevation provides (if (isLight) -neutralize else LocalAbsoluteTonalElevation.current),
        content = content,
    )
}
