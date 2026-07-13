package com.example.coaching_app.view.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.RememberObserver
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.coaching_app.view.main.NavigationData
import com.example.coaching_app.view.component.SearchBarEdit
import com.example.coaching_app.view.component.TopBar
import com.example.coaching_app.view.main.MainScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController ,
    drawerStateChanges : () -> Unit,
    viewModel : HomeScreenViewModel = viewModel()
) {


    var expand by remember { mutableStateOf(false) }
    var searchExpand by remember { mutableStateOf(false)}
    var notificationsCount by remember { mutableStateOf(0) }

    Scaffold(

        topBar = {

            TopBar(
                drawerState = drawerStateChanges,
                searchExpand = { searchExpand = true },
                placeHolderTitle = "Search",
                notificationsCount = notificationsCount,
                increaseCount = { notificationsCount++ },
                expandMenu = { expand = it },
                menuState = expand,
                items = NavigationData(),
                navigateDest = { navController.navigate(it) }
            )

        }

    ){innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
        ){

            items(viewModel.getCardData()){item ->
                HomeCard(item)
            }

        }

    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ){
        if(searchExpand){
            SearchBarEdit(
                isBack = {
                    searchExpand = false
                },
            )
        }
    }

}

@Composable
fun HomeCard(item: CardData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(400.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Image(
                painter = painterResource(id = item.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = item.description,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = item.time,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black,
                fontSize = 15.sp
            )

            HorizontalDivider()

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = "₹" + calculatePriceWithDiscount(item.actual_price.toInt(), item.discount.toInt()).toString() + "/mo",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = item.actual_price + "/mo",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black,
                    fontSize = 15.sp,
                    textDecoration = TextDecoration.LineThrough
                )

                Text(
                    text = item.discount + "% OFF",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black,
                    fontSize = 15.sp
                )

            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ){

                Button(
                    onClick = {},
                    modifier = Modifier.weight(1f)
                ){
                    Text("Buy now")
                }

                OutlinedButton(
                    onClick = {},
                    modifier = Modifier.weight(1f)
                ) {
                    Text("View details")
                }
            }

        }
    }
}

fun calculatePriceWithDiscount(price : Int, discount : Int) : Int {
    return price * discount / 100
}


@Composable
@Preview(showSystemUi = true)

fun MainScreenPreview() {

    val NavController = rememberNavController()

    HomeScreen(
        navController = NavController,
        drawerStateChanges = {}
    )

}