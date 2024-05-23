package com.piriurna.tournamentmanager.android.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationItemUi(
    val icon: ImageVector,
    val text: String,
    val destination: String
) {
    data object Home: BottomNavigationItemUi(
        icon = Icons.Default.Home,
        text = "Home",
        destination = "home_destination"
    )

    data object Profile: BottomNavigationItemUi(
        icon = Icons.Default.AccountCircle,
        text = "Account",
        destination = "account_destination"
    )
}