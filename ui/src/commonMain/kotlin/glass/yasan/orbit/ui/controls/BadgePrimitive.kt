package glass.yasan.orbit.ui.controls

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import glass.yasan.orbit.ui.OrbitTheme
import glass.yasan.orbit.ui.controls.internal.background
import glass.yasan.orbit.ui.foundation.ContentEmphasis
import glass.yasan.orbit.ui.foundation.LocalContentColor
import glass.yasan.orbit.ui.foundation.LocalContentEmphasis
import glass.yasan.orbit.ui.foundation.LocalTextStyle
import glass.yasan.orbit.ui.foundation.ProvideMergedTextStyle
import glass.yasan.orbit.ui.foundation.contentColorFor

@Composable
public fun BadgePrimitive(
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    borderStroke: BorderStroke? = null,
    contentColor: Color = contentColorFor(backgroundColor),
    backgroundBrush: Brush? = null,
    horizontalArrangement: Arrangement.Horizontal = BadgeDefaults.HorizontalArrangement,
    verticalAlignment: Alignment.Vertical = BadgeDefaults.VerticalAlignment,
    contentPadding: PaddingValues = BadgeDefaults.ContentPadding,
    icon: @Composable RowScope.() -> Unit = {},
    content: @Composable RowScope.() -> Unit,
) {
    // remove style color to fallback to proper LocalContentColor
    val textStyle = LocalTextStyle.current.copy(
        color = Color.Unspecified,
    )
    CompositionLocalProvider(
        LocalContentEmphasis provides ContentEmphasis.Normal,
        LocalContentColor provides contentColor,
        LocalTextStyle provides textStyle,
    ) {
        Row(
            modifier = modifier
                .then(if (borderStroke != null) Modifier.border(borderStroke, BadgeShape) else Modifier)
                .background(backgroundColor, backgroundBrush, BadgeShape)
                .padding(contentPadding),
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = verticalAlignment,
        ) {
            ProvideMergedTextStyle(OrbitTheme.typography.bodySmallMedium) {
                icon()
                content()
            }
        }
    }
}

public object BadgeDefaults {
    public val HorizontalArrangement: Arrangement.Horizontal =
        Arrangement.spacedBy(4.dp)
    public val VerticalAlignment: Alignment.Vertical =
        Alignment.CenterVertically
    public val ContentPadding: PaddingValues =
        PaddingValues(horizontal = 8.dp, vertical = 4.dp)
}

internal val BadgeShape = RoundedCornerShape(50)
