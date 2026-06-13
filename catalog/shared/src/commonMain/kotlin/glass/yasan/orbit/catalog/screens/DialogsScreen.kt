package glass.yasan.orbit.catalog.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import glass.yasan.orbit.catalog.Destinations
import glass.yasan.orbit.ui.controls.ButtonSecondary
import glass.yasan.orbit.ui.controls.Scaffold
import glass.yasan.orbit.ui.controls.Separator
import glass.yasan.orbit.ui.controls.Text
import glass.yasan.orbit.ui.controls.TopAppBar

@Composable
internal fun DialogsScreen(
    navController: NavController,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dialogs") },
                onNavigateUp = navController::navigateUp,
            )
        },
    ) { contentPadding: PaddingValues ->
        DialogsScreenInner(
            onShowOrbitDialog = { navController.navigate(Destinations.DialogOrbit) },
            onShowMaterialDialog = { navController.navigate(Destinations.DialogMaterial) },
            contentPadding = contentPadding,
        )
    }
}

@Composable
private fun DialogsScreenInner(
    onShowOrbitDialog: () -> Unit,
    onShowMaterialDialog: () -> Unit,
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
    }
}
