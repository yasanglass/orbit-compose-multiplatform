package glass.yasan.orbit.icons

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import org.jetbrains.compose.resources.painterResource
import glass.yasan.orbit.icons.generated.Res
import glass.yasan.orbit.icons.generated.*

/**
 * TopAppBarIcons are to be used as a navigation icon in TopAppBar and its variants.
 */
@Suppress("unused")
public object TopAppBarIcons {
    public val Back: Painter
        @Composable
        get() = painterResource(Res.drawable.ic_top_app_bar_back)

    public val Close: Painter
        @Composable
        get() = painterResource(Res.drawable.ic_top_app_bar_close)
}
