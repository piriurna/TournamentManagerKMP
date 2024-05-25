package com.piriurna.tournamentmanager.android.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.piriurna.tournamentmanager.android.R
import com.piriurna.tournamentmanager.android.dashboard.navigation.DashboardDestination
import com.piriurna.tournamentmanager.android.profile.navigation.ProfileDestination
import com.piriurna.tournamentmanager.android.tournament.navigation.TournamentDestinations
import com.piriurna.tournamentmanager.android.tournament.navigation.TournamentDestinations.TournamentsOverviewDestination

sealed class BottomNavigationItemUi(
    @DrawableRes val icon: Int,
    @StringRes val text: Int,
    val destination: String
) {
    data object Home: BottomNavigationItemUi(
        icon = R.drawable.ic_homepage,
        text = R.string.home,
        destination = DashboardDestination
    )

    data object Profile: BottomNavigationItemUi(
        icon = R.drawable.ic_profile,
        text = R.string.account,
        destination = ProfileDestination
    )

    data object Tournaments: BottomNavigationItemUi(
        icon = R.drawable.ic_tournament,
        text = R.string.tournaments,
        destination = TournamentsOverviewDestination
    )
}