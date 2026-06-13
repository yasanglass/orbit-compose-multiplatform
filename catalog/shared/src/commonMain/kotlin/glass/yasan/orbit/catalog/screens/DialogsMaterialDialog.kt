package glass.yasan.orbit.catalog.screens

import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import glass.yasan.orbit.ui.controls.Text

@Composable
internal fun DialogsMaterialDialog(navController: NavController) {
    DialogsMaterialDialog(
        onClose = { navController.popBackStack() },
    )
}

@Composable
private fun DialogsMaterialDialog(
    onClose: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onClose,
        title = { Text("Title") },
        text = { Text("Some message.") },
        confirmButton = {
            androidx.compose.material3.TextButton(onClick = onClose) {
                Text("Confirm")
            }
        },
        dismissButton = {
            androidx.compose.material3.TextButton(onClick = onClose) {
                Text("Cancel")
            }
        },
    )
}
