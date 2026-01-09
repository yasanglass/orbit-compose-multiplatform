package glass.yasan.orbit.ui.controls

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import glass.yasan.orbit.icons.Icons
import glass.yasan.orbit.ui.OrbitTheme
import glass.yasan.orbit.ui.generated.Res
import glass.yasan.orbit.ui.generated.*
import org.jetbrains.compose.resources.stringResource
import glass.yasan.orbit.ui.controls.internal.OrbitPreviews
import glass.yasan.orbit.ui.controls.internal.Preview
import glass.yasan.orbit.ui.foundation.ContentEmphasis
import glass.yasan.orbit.ui.foundation.LocalContentColor
import glass.yasan.orbit.ui.foundation.LocalContentEmphasis
import glass.yasan.orbit.ui.foundation.LocalTextStyle
import glass.yasan.orbit.ui.foundation.contentColorFor
import glass.yasan.orbit.ui.layout.alignByLineHeight

@Composable
public fun Tag(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    active: Boolean = false,
    onSelect: (() -> Unit)? = null,
    onRemove: (() -> Unit)? = null,
    removeContentDescription: String = stringResource(Res.string.orbit_cd_tag_remove),
    content: @Composable RowScope.() -> Unit,
) {
    val backgroundColor = when {
        selected -> OrbitTheme.colors.info.normal
        active -> OrbitTheme.colors.info.subtle
        else -> OrbitTheme.colors.surface.subtle
    }
    val contentColor = contentColorFor(backgroundColor)
    val shape = OrbitTheme.shapes.normal

    CompositionLocalProvider(
        LocalTextStyle provides OrbitTheme.typography.bodyNormalMedium,
        LocalContentColor provides contentColor,
        LocalContentEmphasis provides ContentEmphasis.Normal,
    ) {
        Row(
            modifier
                .clip(shape)
                .background(backgroundColor, shape)
                .then(if (onSelect != null) Modifier.clickable(onClick = onSelect) else Modifier)
                .semantics {
                    this.selected = selected
                }
                .padding(horizontal = 8.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            content()

            if (onRemove != null) {
                IconButton(
                    onClick = onRemove,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .alignByLineHeight()
                        .size(16.dp),
                ) {
                    Icon(
                        Icons.CloseCircle,
                        contentDescription = removeContentDescription,
                        emphasis = ContentEmphasis.Minor,
                    )
                }
            }
        }
    }
}

@OrbitPreviews
@Composable
internal fun TagPreview() {
    Preview {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Tag {
                Text("Simple")
            }
            Tag(active = true) {
                Text("Active")
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Tag(selected = true) {
                Text("Selected")
            }
            Tag(active = true, selected = true) {
                Text("Active & Selected")
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Tag(onRemove = {}) {
                Text("Simple")
            }
            Tag(active = true, onRemove = {}) {
                Text("Active")
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Tag(selected = true, onRemove = {}) {
                Text("Selected")
            }
            Tag(active = true, selected = true, onRemove = {}) {
                Text("Active & Selected")
            }
        }
    }
}
