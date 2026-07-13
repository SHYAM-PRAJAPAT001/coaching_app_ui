package com.example.coaching_app.view.main

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NotificationButton(
    count : Int,
    onClick : () -> Unit,
    modifier: Modifier = Modifier
){

    IconButton(
        onClick = onClick,
        modifier = Modifier.size(60.dp)
    ){

        BadgedBox(
            badge = {
                if (count > 0) {
                    Badge {
                        Text(
                            text = if (count > 99)
                                "99+"
                            else
                                count.toString()
                        )
                    }
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notifications"
            )
        }

    }

}