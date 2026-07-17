package com.example.coaching_app.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.coaching_app.view.main.DrawerItem
import com.example.coaching_app.view.main.NotificationButton

@Composable
fun TopBar(
    drawerState : () -> Unit,
    searchExpand : () -> Unit,
    placeHolderTitle : String,
    notificationsCount : Int,
    increaseCount : () -> Unit,
    expandMenu : (Boolean) -> Unit,
    menuState : Boolean,
    items : List<DrawerItem>,
    navigateDest : (String) -> Unit
){

        val screenSize = LocalConfiguration.current.screenWidthDp.dp

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(40.dp)
        ){


            Row(
                modifier = Modifier.weight(0.6f),
                verticalAlignment = Alignment.CenterVertically
            ){
                IconButton(
                    onClick = {
                        drawerState()
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Row(
                    modifier = Modifier
                        .weight(1f)
                        .height(30.dp)
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(10.dp)
                        ).clickable(
                            onClick = {
                                searchExpand()
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                ){

                    Spacer(modifier = Modifier.width(5.dp))
                    Icon(
                        Icons.Outlined.Search,
                        contentDescription = placeHolderTitle,
                        modifier = Modifier
                            .size(25.dp)
                            .padding(horizontal = 3.dp)
                    )

                    Text(text = placeHolderTitle)
                }
            }



            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(0.3f)
            ){

                NotificationButton(
                    count = notificationsCount,
                    onClick = {
                        increaseCount()
                    } ,
                )


                IconButton(
                    onClick = {
                        expandMenu(true)
                    }
                ){
                    Icon(
                        Icons.Outlined.MoreVert,
                        contentDescription = null
                    )
                }
            }

            Box{

                DropdownMenu(
                    expanded = menuState,
                    onDismissRequest = {
                        expandMenu(false)
                    }
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Center
                    ){

                        items.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(item.text) },
                                onClick = {
                                    expandMenu(false)
                                    navigateDest(item.navigation)
                                },
                                leadingIcon = {
                                    Icon(
                                        item.icon,
                                        item.text
                                    )
                                }
                            )
                        }
                    }
                }
            }


        }
}
