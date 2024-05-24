package com.piriurna.tournamentmanager.android.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class IconPosition {
    LEADING, TRAILING
}
@Composable
fun IconText(
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector,
    iconPosition: IconPosition = IconPosition.LEADING,
    iconColor: Color = MaterialTheme.colorScheme.onBackground,
    iconSize: Dp = 16.dp,
    textStyle: TextStyle = LocalTextStyle.current,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    iconTextSpacing: Dp = 4.dp
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if(iconPosition == IconPosition.LEADING) {
            Image(
                modifier = Modifier.size(iconSize),
                imageVector = icon,
                contentDescription = "",
                colorFilter = ColorFilter.tint(iconColor)
            )
            Spacer(modifier = Modifier.width(iconTextSpacing))
        }

        Text(
            modifier = Modifier.weight(1f, fill = false),
            text = text,
            style = textStyle,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = textColor
        )

        if(iconPosition == IconPosition.TRAILING) {
            Spacer(modifier = Modifier.width(iconTextSpacing))
            Image(
                modifier = Modifier.size(iconSize),
                imageVector = icon,
                contentDescription = "",
                colorFilter = ColorFilter.tint(iconColor)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun IconTextLeadingPreview() {
    IconText(text = "This is a test text", icon = Icons.Default.Face)
}

@Preview(showBackground = true)
@Composable
private fun IconTextTrailingPreview() {
    IconText(text = "This is a test text", icon = Icons.Default.Face, iconPosition = IconPosition.TRAILING)
}