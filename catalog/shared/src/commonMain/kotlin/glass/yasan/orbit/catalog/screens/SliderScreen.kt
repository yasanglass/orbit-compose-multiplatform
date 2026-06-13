package glass.yasan.orbit.catalog.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import glass.yasan.orbit.ui.OrbitTheme
import glass.yasan.orbit.ui.controls.RangeSlider
import glass.yasan.orbit.ui.controls.Scaffold
import glass.yasan.orbit.ui.controls.Slider
import glass.yasan.orbit.ui.controls.Text
import glass.yasan.orbit.ui.controls.TopAppBar

@Composable
internal fun SliderScreen(onNavigateUp: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Slider") },
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
            SliderScreenInner()
        }
    }
}

@Composable
private fun SliderScreenInner() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text("Slider", style = OrbitTheme.typography.title4)
            var value1 by rememberSaveable { mutableFloatStateOf(0.5f) }
            Slider(
                value = value1,
                onValueChange = { value1 = it },
                valueLabel = { Text(it.toString()) },
                startLabel = { Text("0") },
                endLabel = { Text("1") },
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text("Slider with steps", style = OrbitTheme.typography.title4)
            var value2 by rememberSaveable { mutableFloatStateOf(0.5f) }
            Slider(
                value = value2,
                onValueChange = { value2 = it },
                valueLabel = { Text(it.toString()) },
                startLabel = { Text("Start") },
                endLabel = { Text("End") },
                steps = 3,
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text("RangeSlider", style = OrbitTheme.typography.title4)
            var value3 by remember { mutableStateOf(0.25f..0.75f) }
            RangeSlider(
                value = value3,
                onValueChange = { value3 = it },
                valueLabel = { Text(it.toString()) },
                startLabel = { Text("Start") },
                endLabel = { Text("End") },
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text("RangeSlider with steps", style = OrbitTheme.typography.title4)
            var value4 by remember { mutableStateOf(0.25f..0.75f) }
            RangeSlider(
                value = value4,
                onValueChange = { value4 = it },
                valueLabel = { Text(it.toString()) },
                startLabel = { Text("Start") },
                endLabel = { Text("End") },
                steps = 3,
            )
        }
    }
}
