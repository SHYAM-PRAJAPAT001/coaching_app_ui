package com.example.coaching_app.view.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LiveTv
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.rounded.MenuBook
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.coaching_app.model.BottomNavItem
import com.example.coaching_app.navigation.Routes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex


@Composable
fun BottomNavigationBar(navController: NavController , modifier : Modifier = Modifier) {

    val backStackState = navController.currentBackStackEntryAsState()
    val currentRoute = backStackState.value?.destination?.route

    val listOfScreens = listOf(
        BottomNavItem(
            name = "Home",
            navigation = Routes.HOME.route ,
            isSelected = Icons.Filled.Home ,
            unSelected = Icons.Outlined.Home
        ),
        BottomNavItem(
            name = "Live",
            navigation = Routes.LIVE.route ,
            isSelected = Icons.Filled.LiveTv ,
            unSelected = Icons.Outlined.LiveTv
        ),
        BottomNavItem(
            name = "Courses",
            navigation = Routes.COURSES.route,
            isSelected = Icons.Filled.MenuBook ,
            unSelected = Icons.Outlined.MenuBook
        ),
        BottomNavItem(
            name = "Profile",
            navigation = Routes.PROFILE.route ,
            isSelected = Icons.Filled.Person ,
            unSelected = Icons.Outlined.Person
        ),
    )


    NavigationBar{

        listOfScreens.forEach { item ->
            NavigationBarItem(
                label = {
                    Text(item.name)
                },
                selected = currentRoute == item.navigation,
                onClick = {
                    navController.navigate(item.navigation){
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        if(currentRoute == item.navigation) item.isSelected
                        else item.unSelected
                        , contentDescription = item.name
                    )
                },
                alwaysShowLabel = true
            )
        }

    }
}

