package glass.yasan.orbit.ui

import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import glass.yasan.orbit.ui.foundation.Colors
import glass.yasan.orbit.ui.foundation.ContentEmphasis
import glass.yasan.orbit.ui.foundation.ElevationLevels
import glass.yasan.orbit.ui.foundation.LocalColors
import glass.yasan.orbit.ui.foundation.LocalContentColor
import glass.yasan.orbit.ui.foundation.LocalContentEmphasis
import glass.yasan.orbit.ui.foundation.LocalShapes
import glass.yasan.orbit.ui.foundation.LocalTypography
import glass.yasan.orbit.ui.foundation.ProvideMergedTextStyle
import glass.yasan.orbit.ui.foundation.Shapes
import glass.yasan.orbit.ui.foundation.Typography
import glass.yasan.orbit.ui.foundation.rememberTextSelectionColors

@Composable
public fun OrbitTheme(
    colors: Colors = OrbitTheme.colors,
    typography: Typography = OrbitTheme.typography,
    shapes: Shapes = OrbitTheme.shapes,
    content: @Composable () -> Unit,
) {
    val selectionColors = rememberTextSelectionColors(colors)

    MaterialTheme(
        colorScheme = colors.toMaterial3Colors(),
        typography = typography.toMaterial3Typography(),
        shapes = shapes.toMaterial3Shapes(),
    ) {
        CompositionLocalProvider(
            // Orbit
            LocalColors provides colors,
            LocalContentEmphasis provides ContentEmphasis.Normal,
            LocalShapes provides shapes,
            LocalTypography provides typography,
            // Foundation
            LocalContentColor provides colors.content.normal,
            LocalTextSelectionColors provides selectionColors,
        ) {
            ProvideMergedTextStyle(typography.bodyNormal, content)
        }
    }
}

public object OrbitTheme {
    public val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    public val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    public val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current

    public val elevations: ElevationLevels = ElevationLevels
}
