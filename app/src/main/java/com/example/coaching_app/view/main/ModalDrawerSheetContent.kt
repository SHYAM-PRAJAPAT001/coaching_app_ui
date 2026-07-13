package com.example.coaching_app.view.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.coaching_app.navigation.Routes

@Composable
fun ModalDrawerSheetContent(
    closeDrawer : () -> Unit,
    navController: NavController
){

    val items = listOf(
        DrawerItem(
            text = "Home",
            des = "Home",
            navigation = Routes.HOME.route,
            icon = Icons.Filled.Home
        ),
        DrawerItem(
            text = "Profile",
            des = "Profile",
            navigation = Routes.PROFILE.route,
            icon = Icons.Outlined.Person
        ),
        DrawerItem(
            text = "Live Classes",
            des = "Live Classes",
            navigation = Routes.LIVE.route,
            icon = Icons.Filled.LiveTv
        ),
        DrawerItem(
            text = "Courses",
            des = "Courses",
            navigation = Routes.COURSES.route,
            icon = Icons.Filled.MenuBook
        ),
        DrawerItem(
            text = "Downloads",
            des = "Downloads",
            navigation = Routes.DOWNLOAD.route,
            icon = Icons.Filled.Download
        )
    )
    ModalDrawerSheet {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
        ){

            Icon(
                Icons.Outlined.Person ,
                contentDescription = "Profile",
                modifier = Modifier
                    .size(70.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = "Profile",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.height(20.dp))


            items.forEach{item ->

                DrawerItems(
                    text = item.text,
                    icon = item.icon,
                    contentDesc = item.des,
                    navController = navController,
                    closeDrawer = closeDrawer,
                    navigation = item.navigation,
                    modifier= Modifier.padding(15.dp)
                )

                HorizontalDivider()
            }
        }
    }
}

data class DrawerItem(
    val text : String,
    val des : String,
    val navigation : String,
    val icon : ImageVector
)