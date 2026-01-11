package glass.yasan.orbit.ui.controls

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import glass.yasan.orbit.icons.generated.Res
import glass.yasan.orbit.ui.OrbitTheme
import glass.yasan.orbit.ui.controls.internal.OrbitPreviews
import glass.yasan.orbit.ui.controls.internal.Preview
import glass.yasan.orbit.ui.foundation.LocalTextStyle
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.decodeToImageBitmap

/**
 * Implementation of [Orbit CountryFlag](https://orbit.kiwi/components/countryflag/)
 *
 * - Size is resolved to current line-height. To modify the size, use [Modifier.size]. Defaults to 20.sp.
 */
@OptIn(ExperimentalResourceApi::class)
@Composable
public fun CountryFlag(
    countryCode: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
) {
    val code = countryCode.lowercase()

    val bitmap by produceState<ImageBitmap?>(initialValue = null, key1 = code) {
        value = runCatching {
            Res.readBytes("files/country_flags/$code.png").decodeToImageBitmap()
        }.getOrElse {
            Res.readBytes(DefaultFlagPath).decodeToImageBitmap()
        }
    }

    // Default size -> 20.sp (~20.dp), bodyNormal's line-height.
    val lineHeight = with(LocalDensity.current) {
        LocalTextStyle.current.lineHeight.toDp()
    }

    val imageModifier = modifier
        .size(lineHeight)
        .aspectRatio(12 / 7f)
        .clip(RoundedCornerShape(2.dp))
        .border(
            width = 0.5.dp,
            color = OrbitTheme.colors.content.normal.copy(alpha = .15f),
            shape = RoundedCornerShape(2.dp),
        )

    bitmap?.let {
        Image(
            painter = BitmapPainter(it),
            contentDescription = contentDescription,
            contentScale = ContentScale.Fit,
            modifier = imageModifier,
        )
    } ?: Box(modifier = imageModifier)
}

private const val DefaultFlagPath = "files/country_flags/orbit_country_flag_undefined.png"

@OrbitPreviews
@Composable
internal fun CountryFlagPreview() {
    Preview {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            CountryFlag(countryCode = "cz", contentDescription = null)
            CountryFlag(countryCode = "sk", contentDescription = null)
            CountryFlag(countryCode = "", contentDescription = null)
        }
    }
}
