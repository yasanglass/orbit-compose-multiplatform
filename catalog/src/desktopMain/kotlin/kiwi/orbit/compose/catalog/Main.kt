package kiwi.orbit.compose.catalog

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Orbit Compose Catalog",
    ) {
        App()
    }
}
