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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import glass.yasan.orbit.ui.controls.Scaffold
import glass.yasan.orbit.ui.controls.SeatExtraLegroom
import glass.yasan.orbit.ui.controls.SeatLegendExtraLegroom
import glass.yasan.orbit.ui.controls.SeatLegendStandard
import glass.yasan.orbit.ui.controls.SeatLegendUnavailable
import glass.yasan.orbit.ui.controls.SeatStandard
import glass.yasan.orbit.ui.controls.SeatUnavailable
import glass.yasan.orbit.ui.controls.Text
import glass.yasan.orbit.ui.controls.TopAppBar

@Composable
internal fun SeatScreen(onNavigateUp: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Seat") },
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
            SeatScreenInner()
        }
    }
}

@Composable
private fun SeatScreenInner() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            SeatLegendExtraLegroom { Text("Extra legroom ($5.99 – $12.98)") }
            SeatLegendStandard { Text("Standard ($5.99 – $12.98)") }
            SeatLegendUnavailable { Text("Unavailable") }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Row {
                var seatSelected by rememberSaveable { mutableStateOf(false) }
                SeatExtraLegroom(
                    selected = seatSelected,
                    label = { Text("A") },
                    price = { Text("€19.99") },
                    onClick = { seatSelected = !seatSelected },
                )

                var seatSelected2 by rememberSaveable { mutableStateOf(false) }
                SeatStandard(
                    selected = seatSelected2,
                    label = { Text("B") },
                    price = { Text("€12.99") },
                    onClick = { seatSelected2 = !seatSelected2 },
                )

                SeatUnavailable(
                    contentDescription = "Unavailable",
                )
            }

            Row {
                var seatSelected3 by rememberSaveable { mutableStateOf(true) }
                SeatExtraLegroom(
                    selected = seatSelected3,
                    label = { Text("MO") },
                    price = { Text("€19.99") },
                    onClick = { seatSelected3 = !seatSelected3 },
                )

                var seatSelected4 by rememberSaveable { mutableStateOf(true) }
                SeatStandard(
                    selected = seatSelected4,
                    label = { Text("MO") },
                    price = { Text("€12.99") },
                    onClick = { seatSelected4 = !seatSelected4 },
                )

                SeatUnavailable(
                    contentDescription = "Unavailable",
                )
            }
        }
    }
}
