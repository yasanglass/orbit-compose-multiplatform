package kiwi.orbit.compose.ui.controls.internal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kiwi.orbit.compose.ui.OrbitTheme
import kiwi.orbit.compose.ui.controls.Surface
import kiwi.orbit.compose.ui.foundation.lightColors
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun Preview(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    OrbitTheme(colors = lightColors()) {
        Surface {
            Column(
                modifier = modifier.padding(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                content()
            }
        }
    }
}

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
@Preview
internal annotation class OrbitPreviews
