package glass.yasan.orbit.ui.controls

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import glass.yasan.orbit.ui.foundation.ContentEmphasis
import glass.yasan.orbit.ui.foundation.LocalContentEmphasis
import glass.yasan.orbit.ui.foundation.ProvideContentEmphasis

@Composable
public fun IconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    rippleRadius: Dp = RippleRadius,
    minimumInteractiveComponentEnforcement: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .clickable(
                onClick = onClick,
                enabled = enabled,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = ripple(bounded = false, radius = rippleRadius),
            )
            .run {
                if (minimumInteractiveComponentEnforcement) {
                    size(rippleRadius * 2)
                } else {
                    this
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        val contentEmphasis = if (enabled) LocalContentEmphasis.current else ContentEmphasis.Disabled
        ProvideContentEmphasis(contentEmphasis, content)
    }
}

private val RippleRadius = 24.dp
