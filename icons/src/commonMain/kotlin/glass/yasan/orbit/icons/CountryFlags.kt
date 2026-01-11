package glass.yasan.orbit.icons

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import glass.yasan.orbit.icons.generated.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.decodeToImageBitmap

/**
 * Provides access to country flag images.
 *
 * Usage:
 * ```
 * val flagBitmap = CountryFlags.bitmap("us")
 * Image(
 *     bitmap = flagBitmap,
 *     contentDescription = "United States flag"
 * )
 * ```
 *
 * Or using the painter helper:
 * ```
 * Image(
 *     painter = BitmapPainter(CountryFlags.bitmap("us")),
 *     contentDescription = "United States flag"
 * )
 * ```
 */
@OptIn(ExperimentalResourceApi::class)
public object CountryFlags {
    private val cache = mutableMapOf<String, ImageBitmap>()

    /**
     * Loads the country flag bitmap for the given ISO 3166-1 alpha-2 country code.
     *
     * @param countryCode ISO 3166-1 alpha-2 country code (e.g., "us", "gb", "de")
     * @return An [ImageBitmap] for the country flag
     */
    public suspend fun bitmap(countryCode: String): ImageBitmap {
        val normalizedCode = countryCode.lowercase()
        return cache.getOrPut(normalizedCode) {
            runCatching {
                Res.readBytes("files/country_flags/$normalizedCode.png").decodeToImageBitmap()
            }.getOrElse {
                Res.readBytes(DefaultFlagPath).decodeToImageBitmap()
            }
        }
    }

    /**
     * Returns a [Painter] for the country flag. Must be called within a suspending context
     * or use the cached bitmap if available.
     *
     * @param countryCode ISO 3166-1 alpha-2 country code (e.g., "us", "gb", "de")
     * @return A [BitmapPainter] for the country flag
     */
    public suspend fun painter(countryCode: String): Painter {
        return BitmapPainter(bitmap(countryCode))
    }
}

private const val DefaultFlagPath = "files/country_flags/orbit_country_flag_undefined.png"
