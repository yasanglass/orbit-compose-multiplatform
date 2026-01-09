package kiwi.orbit.compose.catalog.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import kiwi.orbit.compose.catalog.Destinations
import kiwi.orbit.compose.ui.controls.ButtonSecondary
import kiwi.orbit.compose.ui.controls.Scaffold
import kiwi.orbit.compose.ui.controls.Separator
import kiwi.orbit.compose.ui.controls.Text
import kiwi.orbit.compose.ui.controls.TopAppBar
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

@Composable
internal fun DialogsScreen(
    navController: NavController,
) {
    val timeResult by navController.currentBackStackEntry
        ?.savedStateHandle
        ?.getStateFlow<LocalTime?>("time_result", null)
        ?.collectAsStateWithLifecycle() ?: remember { mutableStateOf(null) }

    val dateResult by navController.currentBackStackEntry
        ?.savedStateHandle
        ?.getStateFlow<LocalDate?>("date_result", null)
        ?.collectAsStateWithLifecycle() ?: remember { mutableStateOf(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dialogs") },
                onNavigateUp = navController::navigateUp,
            )
        },
    ) { contentPadding: PaddingValues ->
        DialogsScreenInner(
            time = timeResult,
            date = dateResult,
            onShowOrbitDialog = { navController.navigate(Destinations.DialogOrbit) },
            onShowMaterialDialog = { navController.navigate(Destinations.DialogMaterial) },
            onShowMaterialTimePicker = { navController.navigate(Destinations.DialogMaterialTimePicker) },
            onShowMaterialDatePicker = { navController.navigate(Destinations.DialogMaterialDatePicker) },
            contentPadding = contentPadding,
        )
    }
}

@Composable
private fun DialogsScreenInner(
    time: LocalTime?,
    date: LocalDate?,
    onShowOrbitDialog: () -> Unit,
    onShowMaterialDialog: () -> Unit,
    onShowMaterialTimePicker: () -> Unit,
    onShowMaterialDatePicker: () -> Unit,
    contentPadding: PaddingValues,
) {
    Column(
        Modifier
            .fillMaxSize()
            .wrapContentHeight()
            .verticalScroll(rememberScrollState())
            .padding(contentPadding)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ButtonSecondary(
            onClick = onShowOrbitDialog,
            content = { Text("Show Orbit Dialog") },
        )
        Separator(Modifier.padding(vertical = 16.dp))
        ButtonSecondary(
            onClick = onShowMaterialDialog,
            content = { Text("Show M3 Dialog") },
        )
        ButtonSecondary(
            onClick = onShowMaterialTimePicker,
            content = { Text("Show M3 TimePicker") },
        )
        ButtonSecondary(
            onClick = onShowMaterialDatePicker,
            content = { Text("Show M3 DatePicker") },
        )
        Text("Picked Time: $time")
        Text("Picked Date: $date")
    }
}
