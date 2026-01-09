package glass.yasan.orbit.ui.utils

import androidx.compose.ui.MotionDurationScale
import kotlin.coroutines.CoroutineContext

internal val CoroutineContext.durationScale: Float
    get() {
        val scale = this[MotionDurationScale]?.scaleFactor ?: 1f
        check(scale >= 0f)
        return scale
    }
