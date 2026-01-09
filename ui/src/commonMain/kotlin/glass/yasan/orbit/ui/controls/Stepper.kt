@file:Suppress("MatchingDeclarationName")

package glass.yasan.orbit.ui.controls

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.AccessibilityAction
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
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

public object StepperSemanticsActions {
    public val IncreaseValue: SemanticsPropertyKey<AccessibilityAction<() -> Boolean>> =
        SemanticsPropertyKey("IncreaseValue")
    public val DecreaseValue: SemanticsPropertyKey<AccessibilityAction<() -> Boolean>> =
        SemanticsPropertyKey("DecreaseValue")
}

@Composable
public fun Stepper(
    value: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    active: Boolean = value > 0,
    minValue: Int = 0,
    maxValue: Int = Int.MAX_VALUE,
) {
    check(value in minValue..maxValue) { "Provided value is out of required bounds." }

    Stepper(
        value = value,
        active = active,
        onValueChange = onValueChange,
        modifier = modifier,
        valueValidator = { newValue ->
            newValue in minValue..maxValue
        },
    )
}

@Composable
public fun Stepper(
    value: Int,
    active: Boolean,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    addContentDescription: String = stringResource(Res.string.orbit_cd_stepper_add),
    removeContentDescription: String = stringResource(Res.string.orbit_cd_stepper_remove),
    valueValidator: ((Int) -> Boolean)? = null,
) {
    StepperPrimitive(
        value = value,
        active = active,
        onValueChange = onValueChange,
        addContentDescription = addContentDescription,
        removeContentDescription = removeContentDescription,
        valueValidator = valueValidator,
        modifier = modifier,
    )
}

@Composable
private fun StepperPrimitive(
    value: Int,
    active: Boolean,
    onValueChange: (Int) -> Unit,
    addContentDescription: String,
    removeContentDescription: String,
    valueValidator: ((Int) -> Boolean)?,
    modifier: Modifier = Modifier,
) {
    val isDecreaseValid = valueValidator?.invoke(value - 1) ?: true
    val isIncreaseValid = valueValidator?.invoke(value + 1) ?: true
    Row(
        modifier = modifier.semantics(mergeDescendants = true) {
            if (isDecreaseValid) {
                this[StepperSemanticsActions.DecreaseValue] =
                    AccessibilityAction(null) {
                        onValueChange.invoke(value - 1)
                        true
                    }
            }
            if (isIncreaseValid) {
                this[StepperSemanticsActions.IncreaseValue] =
                    AccessibilityAction(null) {
                        onValueChange.invoke(value + 1)
                        true
                    }
            }
        },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        StepperButton(
            onClick = { onValueChange.invoke(value - 1) },
            active = active,
            enabled = isDecreaseValid,
        ) {
            Icon(Icons.Minus, contentDescription = removeContentDescription)
        }

        StepperLabel(value)

        StepperButton(
            onClick = { onValueChange.invoke(value + 1) },
            active = active,
            enabled = isIncreaseValid,
        ) {
            Icon(Icons.Plus, contentDescription = addContentDescription)
        }
    }
}

@Composable
private fun StepperLabel(
    value: Int,
) {
    AnimatedContent(
        targetState = value,
        transitionSpec = {
            if (targetState > initialState) {
                slideInVertically { height -> height / 2 } + fadeIn() togetherWith
                    slideOutVertically { height -> -height / 2 } + fadeOut()
            } else {
                slideInVertically { height -> -height / 2 } + fadeIn() togetherWith
                    slideOutVertically { height -> height / 2 } + fadeOut()
            }.using(
                SizeTransform(clip = false),
            )
        },
        label = "StepperValue",
    ) { targetNumber ->
        Text(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .widthIn(min = 20.dp),
            text = targetNumber.toString(),
            style = OrbitTheme.typography.bodyLargeMedium,
            emphasis = ContentEmphasis.Normal,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun StepperButton(
    onClick: () -> Unit,
    active: Boolean,
    enabled: Boolean = true,
    content: @Composable () -> Unit,
) {
    val mainBackgroundColor = if (active) {
        OrbitTheme.colors.info.normal
    } else {
        OrbitTheme.colors.surface.normal
    }

    val background = when {
        enabled -> mainBackgroundColor
        active -> mainBackgroundColor.copy(alpha = 0.3f)
        else -> mainBackgroundColor.copy(alpha = 0.5f)
    }

    val contentColor = contentColorFor(mainBackgroundColor)

    Box(
        modifier = Modifier.clickable(
            enabled = enabled,
            onClick = onClick,
            role = Role.Button,
            interactionSource = remember { MutableInteractionSource() },
            indication = ripple(bounded = false, radius = 22.dp),
        ),
    ) {
        CompositionLocalProvider(
            LocalTextStyle provides OrbitTheme.typography.bodyNormal,
            LocalContentColor provides contentColor,
            LocalContentEmphasis provides if (enabled) ContentEmphasis.Normal else ContentEmphasis.Disabled,
        ) {
            Box(
                Modifier
                    .padding(2.dp) // 24.dp size, scale with icon-size scaling
                    .background(background, CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                content()
            }
        }
    }
}

@OrbitPreviews
@Composable
internal fun StepperPreview() {
    Preview {
        Stepper(
            value = 1,
            onValueChange = {},
        )
        Stepper(
            value = 0,
            onValueChange = {},
        )
        var i by remember { mutableIntStateOf(10) }
        Stepper(
            value = i,
            onValueChange = { i = it },
            maxValue = 10,
        )
    }
}
