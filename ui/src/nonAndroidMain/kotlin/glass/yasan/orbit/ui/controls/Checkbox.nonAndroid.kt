package glass.yasan.orbit.ui.controls

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import glass.yasan.orbit.icons.Icons

@Composable
internal actual fun CheckboxIcon(
    checked: Boolean,
    iconColor: Color,
    modifier: Modifier,
) {
    val alpha by animateFloatAsState(
        targetValue = if (checked) 1f else 0f,
        animationSpec = tween(durationMillis = 100),
        label = "CheckboxIconAlpha",
    )
    Icon(
        painter = Icons.Check,
        contentDescription = null,
        modifier = modifier.alpha(alpha),
        tint = iconColor,
    )
}
