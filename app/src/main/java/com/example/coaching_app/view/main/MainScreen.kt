package com.example.coaching_app.view.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coaching_app.navigation.Routes
import com.example.coaching_app.view.courses.CourseScreen
import com.example.coaching_app.view.download.DownloadScreen
import com.example.coaching_app.view.home.HomeScreen
import com.example.coaching_app.view.live.LiveScreen
import com.example.coaching_app.view.profile.ProfileScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val items = NavigationData()
    val screenSize = LocalConfiguration.current.screenWidthDp.dp

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(screenSize * 0.75f)
            ){

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){

                    Icon(
                        Icons.Outlined.Person ,
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(70.dp)
                            .align(Alignment.CenterHorizontally)
                    )


                    Text("Profile" ,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                items.forEach { item ->
                    NavigationDrawerItem(
                        label = {
                            Text(item.text)
                        },
                        selected = Routes.HOME.route == item.navigation,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                                navController.navigate(item.navigation)
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.des) },
                        modifier = Modifier.padding(
                            horizontal = 15.dp,
                            vertical = 5.dp
                        )
                    )
                }
            }
        }
    ) {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(
                    navController,
                    modifier = Modifier
                        .navigationBarsPadding()
                        .padding(10.dp)
                )
            },
        ){innerPadding ->

            NavHost(
                navController = navController,
                startDestination = Routes.HOME.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Routes.HOME.route) {
                    HomeScreen(
                        navController,
                        drawerStateChanges ={
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    )
                }

                composable(Routes.LIVE.route) {
                    LiveScreen(navController,
                        drawerStateChanges = {
                            scope.launch{
                                drawerState.open()
                            }
                        }
                    )
                }

                composable(Routes.COURSES.route) { CourseScreen(navController) }
                composable(Routes.DOWNLOAD.route) { DownloadScreen(navController) }
                composable(Routes.PROFILE.route) { ProfileScreen(navController) }
            }
        }
    }

}

fun NavigationData() : List<DrawerItem> {

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
    )

    return items
}

@Composable
@Preview(showSystemUi = true)

fun MainScreenPreview() {
    MainScreen()
}