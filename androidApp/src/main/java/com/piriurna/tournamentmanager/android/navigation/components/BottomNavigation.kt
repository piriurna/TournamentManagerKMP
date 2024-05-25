package com.piriurna.tournamentmanager.android.navigation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.tournamentmanager.android.navigation.BottomNavigationItemUi

@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    items: List<BottomNavigationItemUi>,
    onNavigate: (String) -> Unit,
    currentDestination: String
) {
    Row(
        modifier = modifier
            .height(56.dp)
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
            ),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items.forEach {
            BottomNavigationItem(
                modifier = modifier
                    .weight(1f),
                bottomNavigationItemUi = it,
                onNavigate = onNavigate,
                isSelected = currentDestination == it.destination
            )
        }
    }
}


@Composable
fun BottomNavigationItem(
    modifier: Modifier = Modifier,
    bottomNavigationItemUi: BottomNavigationItemUi,
    onNavigate: (String) -> Unit,
    isSelected: Boolean
) {

    val backgroundColor = if(isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.9f) else MaterialTheme.colorScheme.surface
    val foregroundColor = if(isSelected) MaterialTheme.colorScheme.inverseOnSurface else MaterialTheme.colorScheme.onSurface

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .clickable { onNavigate(bottomNavigationItemUi.destination) },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(painter = painterResource(bottomNavigationItemUi.icon), contentDescription = "", tint = foregroundColor)
        Text(text = stringResource(id = bottomNavigationItemUi.text), color = foregroundColor)
    }
}


@Preview(showBackground = true)
@Composable
private fun BottomNavigationPreview() {
    var selectedDestination by remember { mutableStateOf(BottomNavigationItemUi.Home.destination) }
    BottomNavigation(
        modifier = Modifier.fillMaxWidth(),
        items = listOf(BottomNavigationItemUi.Home, BottomNavigationItemUi.Profile),
        onNavigate = { selectedDestination = it },
        currentDestination = selectedDestination
    )
}