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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically,

        ){



            IconButton(
                onClick = {
                    drawerState()
                },
            ) {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "Profile",
                    modifier = Modifier
                        .border(
                            shape = CircleShape,
                            width = 1.dp,
                            color = Color.Black
                        )
                        .padding(2.dp),
                    tint = Color.Black
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Row(
                modifier = Modifier
                    .weight(1f)
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

                Icon(
                    Icons.Outlined.Search,
                    contentDescription = placeHolderTitle,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )

                Text(text = placeHolderTitle)
            }

            Spacer(modifier = Modifier.width(10.dp))


            NotificationButton(
                count = notificationsCount,
                onClick = {
                    increaseCount()
                } ,
            )

            Spacer(modifier = Modifier.width(10.dp))

            Box{
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

                DropdownMenu(
                    expanded = menuState,
                    onDismissRequest = {
                        expandMenu(false)
                    }
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
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
