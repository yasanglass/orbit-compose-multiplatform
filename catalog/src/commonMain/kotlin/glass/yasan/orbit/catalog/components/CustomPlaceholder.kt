package glass.yasan.orbit.catalog.components

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
fun CustomPlaceholder(
    modifier: Modifier = Modifier,
    text: String = "Custom content",
) {
    Box(
        modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(OrbitTheme.colors.primary.subtle),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            color = contentColorFor(backgroundColor = OrbitTheme.colors.primary.subtle),
        )
    }
}
