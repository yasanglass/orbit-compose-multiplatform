package glass.yasan.orbit.catalog.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import glass.yasan.orbit.ui.OrbitTheme
import glass.yasan.orbit.ui.controls.Scaffold
import glass.yasan.orbit.ui.controls.Stepper
import glass.yasan.orbit.ui.controls.Text
import glass.yasan.orbit.ui.controls.TopAppBar

@Composable
internal fun StepperScreen(onNavigateUp: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Stepper") },
                onNavigateUp = onNavigateUp,
            )
        },
    ) { contentPadding: PaddingValues ->
        Box(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(contentPadding),
        ) {
            StepperScreenInner()
        }
    }
}

@Preview
@Composable
private fun StepperScreenInner() {
    Column(Modifier.padding(16.dp)) {
        var valueFirst by rememberSaveable { mutableIntStateOf(0) }
        var valueSecond by rememberSaveable { mutableIntStateOf(0) }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 16.dp),
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "How many pets do you have?",
                style = OrbitTheme.typography.title5,
            )

            Stepper(
                value = valueFirst,
                onValueChange = { valueFirst = it },
                maxValue = 10,
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 16.dp),
        ) {
            Text(
                modifier = Modifier.weight(1f),
                style = OrbitTheme.typography.title5,
                text = "How many pets would you like to have?",
            )

            Stepper(
                value = valueSecond,
                active = false,
                onValueChange = { valueSecond = it },
                maxValue = 10,
            )
        }
    }
}
