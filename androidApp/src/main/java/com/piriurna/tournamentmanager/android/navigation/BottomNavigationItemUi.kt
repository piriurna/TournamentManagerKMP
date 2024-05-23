package com.piriurna.tournamentmanager.android.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.piriurna.tournamentmanager.android.R
import com.piriurna.tournamentmanager.android.dashboard.navigation.DashboardDestination
import com.piriurna.tournamentmanager.android.profile.navigation.ProfileDestination

sealed class BottomNavigationItemUi(
    val icon: ImageVector,
    @StringRes val text: Int,
    val destination: String
) {
    data object Home: BottomNavigationItemUi(
        icon = Icons.Default.Home,
        text = R.string.home,
        destination = DashboardDestination
    )

    data object Profile: BottomNavigationItemUi(
        icon = Icons.Default.AccountCircle,
        text = R.string.account,
        destination = ProfileDestination
    )
}