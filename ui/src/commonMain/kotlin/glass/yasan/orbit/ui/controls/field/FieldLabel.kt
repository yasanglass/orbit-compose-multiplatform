package glass.yasan.orbit.ui.controls.field

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import glass.yasan.orbit.ui.OrbitTheme
import glass.yasan.orbit.ui.foundation.ProvideMergedTextStyle
import kotlin.math.max

/**
 * Alignment line holding a value of label's last baseline.
 *
 * With this line, you have vertically align multiple text fields
 * to have their "input boxes" aligned.
 */
public val LabelLastBaseLine: HorizontalAlignmentLine =
    HorizontalAlignmentLine(::max)

@Composable
internal fun FieldLabel(
    content: @Composable () -> Unit,
) {
    ProvideMergedTextStyle(OrbitTheme.typography.bodyNormalMedium) {
        Box(
            Modifier
                .padding(bottom = 4.dp)
                .layout { measurable, constraints ->
                    val placeable = measurable.measure(constraints)
                    layout(
                        placeable.width,
                        placeable.height,
                        mapOf(LabelLastBaseLine to placeable[LastBaseline]),
                    ) {
                        placeable.place(0, 0)
                    }
                },
        ) {
            content()
        }
    }
}
