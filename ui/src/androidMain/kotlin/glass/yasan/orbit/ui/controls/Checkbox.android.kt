package glass.yasan.orbit.ui.controls

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import glass.yasan.orbit.ui.R

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
internal actual fun CheckboxIcon(
    checked: Boolean,
    iconColor: Color,
    modifier: Modifier,
) {
    val animatedImage = AnimatedImageVector.animatedVectorResource(R.drawable.avd_check)
    Icon(
        painter = rememberAnimatedVectorPainter(animatedImage, atEnd = checked),
        contentDescription = null,
        modifier = modifier,
        tint = iconColor,
    )
}
