package glass.yasan.orbit.ui.controls

import androidx.compose.ui.platform.AccessibilityManager
import glass.yasan.orbit.icons.IconName
import kotlin.time.Duration
import kotlinx.coroutines.flow.StateFlow

public interface ToastData {
    public val message: String
    public val iconName: IconName?
    public val imageUrl: String?
    public val actionLabel: String?
    public val toastId: String

    public val animationDuration: StateFlow<Duration?>

    public suspend fun run(accessibilityManager: AccessibilityManager?)

    public fun performAction()

    public fun pause()

    public fun resume()

    public fun dismiss()

    public fun dismissed()
}
