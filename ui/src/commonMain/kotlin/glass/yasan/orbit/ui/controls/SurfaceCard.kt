package glass.yasan.orbit.ui.controls

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import glass.yasan.orbit.ui.OrbitTheme
import glass.yasan.orbit.ui.controls.internal.CustomPlaceholder
import glass.yasan.orbit.ui.controls.internal.OrbitPreviews
import glass.yasan.orbit.ui.controls.internal.Preview
import glass.yasan.orbit.ui.foundation.contentColorFor

@Composable
public fun SurfaceCard(
    modifier: Modifier = Modifier,
    shape: Shape = OrbitTheme.shapes.normal,
    backgroundColor: Color = OrbitTheme.colors.surface.main,
    contentColor: Color = contentColorFor(backgroundColor),
    border: BorderStroke? = null,
    elevation: Dp = OrbitTheme.elevations.Level1,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        border = border,
        content = content,
    )
}

@Composable
public fun SurfaceCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = OrbitTheme.shapes.normal,
    backgroundColor: Color = OrbitTheme.colors.surface.main,
    contentColor: Color = contentColorFor(backgroundColor),
    border: BorderStroke? = null,
    elevation: Dp = OrbitTheme.elevations.Level1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit,
) {
    Surface(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        color = backgroundColor,
        contentColor = contentColor,
        border = border,
        elevation = elevation,
        interactionSource = interactionSource,
        content = content,
    )
}

@Composable
public fun SurfaceCard(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = OrbitTheme.shapes.normal,
    backgroundColor: Color = OrbitTheme.colors.surface.main,
    contentColor: Color = contentColorFor(backgroundColor),
    border: BorderStroke? = null,
    elevation: Dp = OrbitTheme.elevations.Level1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit,
) {
    Surface(
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        color = backgroundColor,
        contentColor = contentColor,
        border = border,
        elevation = elevation,
        interactionSource = interactionSource,
        content = content,
    )
}

@OrbitPreviews
@Composable
internal fun SurfaceCardPreview() {
    Preview {
        SurfaceCard {
            CustomPlaceholder()
        }
    }
}
