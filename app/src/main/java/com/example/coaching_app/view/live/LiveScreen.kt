package com.example.coaching_app.view.live

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.coaching_app.view.component.TopBar
import com.example.coaching_app.view.home.HomeCard
import com.example.coaching_app.view.home.HomeScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiveScreen(navController: NavController,
               drawerStateChanges : () -> Unit,
               viewModel : HomeScreenViewModel = HomeScreenViewModel()
) {

    var messageCount by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically ,
            ){

                IconButton(
                    onClick = {
                        drawerStateChanges()
                    }
                ){
                    Icon(
                        Icons.Outlined.Menu,
                        contentDescription = "Menu",
                    )
                }


                Text(
                    text = "Live Classes",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge
                )

                IconButton(
                    onClick = {messageCount++},
                    modifier = Modifier.size(60.dp)
                ){
                    BadgedBox(
                        badge = {
                            if(messageCount > 0){
                                Badge{
                                    Text(
                                        if(messageCount > 99) "99+"
                                        else messageCount.toString()
                                    )
                                }
                            }
                        }
                    ) {
                        Icon(
                            Icons.Outlined.Notifications,
                            contentDescription = "notifications"
                        )
                    }
                }
            }


        }
    ){innerPadding ->

        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 10.dp,
                    vertical = 10.dp)
        ) {

            item{
                Text("Join Live Classes",
                    modifier = Modifier
                        .fillMaxWidth()
                    ,
                    style = MaterialTheme.typography.titleLarge
                )
            }

            item{
                Box{
                    LiveClassesCarousel()
                }
            }

            items(viewModel.getCardData()){
                HomeCard(it)
            }
        }

    }
}


@Preview
@Composable

fun LiveScreenPreview(){

    val NavController = rememberNavController()
    LiveScreen(NavController, {})
}