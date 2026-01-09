package kiwi.orbit.compose.ui.controls

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil3.compose.LocalPlatformContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import kiwi.orbit.compose.icons.generated.Res
import kiwi.orbit.compose.ui.OrbitTheme
import kiwi.orbit.compose.ui.controls.internal.OrbitPreviews
import kiwi.orbit.compose.ui.controls.internal.Preview
import kiwi.orbit.compose.ui.foundation.LocalTextStyle

/**
 * Implementation of [Orbit CountryFlag](https://orbit.kiwi/components/countryflag/)
 *
 * - Size is resolved to current line-height. To modify the size, use [Modifier.size]. Defaults to 20.sp.
 */
@Composable
public fun CountryFlag(
    countryCode: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
) {
    val code = countryCode.lowercase().ifEmpty { "undefined" }
    val url = Res.getUri("files/country_flags/orbit_country_flag_$code.png")

    // Default size -> 20.sp (~20.dp), bodyNormal's line-height.
    val lineHeight = with(LocalDensity.current) {
        LocalTextStyle.current.lineHeight.toDp()
    }

    Image(
        painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalPlatformContext.current)
                .data(url)
                .crossfade(true)
                .build(),
        ),
        contentScale = ContentScale.Fit,
        contentDescription = contentDescription,
        modifier = modifier
            .size(lineHeight)
            .aspectRatio(12 / 7f)
            .clip(RoundedCornerShape(2.dp))
            .border(
                width = 0.5.dp,
                color = OrbitTheme.colors.content.normal.copy(alpha = .15f),
                shape = RoundedCornerShape(2.dp),
            ),
    )
}

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
