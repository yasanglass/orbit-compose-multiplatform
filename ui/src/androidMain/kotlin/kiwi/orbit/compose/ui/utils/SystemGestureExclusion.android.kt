package kiwi.orbit.compose.ui.utils

import androidx.compose.foundation.systemGestureExclusion
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

internal actual fun Modifier.systemGestureExclusionPadded(padding: Dp): Modifier = composed {
    val density = LocalDensity.current
    val paddingPx = with(density) { padding.toPx() }
    systemGestureExclusion { coordinates ->
        Rect(
            -paddingPx,
            -paddingPx,
            coordinates.size.width + paddingPx,
            coordinates.size.height.toFloat() + paddingPx,
        )
    }
}
