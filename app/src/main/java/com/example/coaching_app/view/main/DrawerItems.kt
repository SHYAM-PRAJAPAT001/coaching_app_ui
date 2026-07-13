package com.example.coaching_app.view.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DrawerItems(
    text : String,
    icon: ImageVector,
    contentDesc : String,
    navController : NavController ,
    closeDrawer : () -> Unit,
    navigation : String,
    modifier: Modifier = Modifier
){

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = modifier.clickable(
            onClick = {
                navController.navigate(navigation)
                closeDrawer()
            }
        )
    ){

        Icon(
            imageVector = icon,
            contentDescription = contentDesc,
        )

        Text(
            text = text,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}