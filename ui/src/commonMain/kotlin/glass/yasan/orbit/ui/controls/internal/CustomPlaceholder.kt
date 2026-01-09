package glass.yasan.orbit.ui.controls.internal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import glass.yasan.orbit.ui.OrbitTheme
import glass.yasan.orbit.ui.controls.Text
import glass.yasan.orbit.ui.foundation.contentColorFor

@Composable
internal fun CustomPlaceholder(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(OrbitTheme.colors.primary.subtle),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Custom content",
            color = contentColorFor(backgroundColor = OrbitTheme.colors.primary.subtle),
        )
    }
}
