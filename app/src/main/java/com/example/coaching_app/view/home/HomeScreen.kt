package com.example.coaching_app.view.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.coaching_app.view.main.NavigationData
import com.example.coaching_app.view.component.SearchBarEdit
import com.example.coaching_app.view.component.SubTitles
import com.example.coaching_app.view.component.TopBar
import com.example.coaching_app.view.home.Cards.PopularCoursesCard
import kotlin.collections.mutableListOf

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

    var selectedField by remember{ mutableStateOf<FilterChips?>(null)}

    var filter by remember{
        mutableStateOf(
            mutableStateOf(
                "All"
            )
        )
    }

    var batchMode by remember{
        mutableStateOf(
            mutableListOf(
                "None"
            )
        )
    }

    var price by remember{
        mutableStateOf(
            mutableListOf(
                "None"
            )
        )
    }

    var language by remember{
        mutableStateOf(
            mutableListOf(
                "None"
            )
        )
    }

    var examType by remember{
        mutableStateOf(
            mutableListOf("None")
        )
    }


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

            item{
                SubTitles("Popular courses \uD83D\uDD25")
            }

            items(viewModel.getCardData()){item ->
                PopularCoursesCard(item)
            }

            item{
                SubTitles("All courses")
            }
            
            item{
                LazyRow(
                    modifier = Modifier.fillMaxWidth()
                        .padding(10.dp) ,
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ){

                    items(ChipField.allField){ item ->

                        FilterChip(
                            selected = selectedField == item,
                            onClick = {
                                selectedField = item
                            },
                            label = {
                                Text(item.name.replace("_", " "))
                            },

                            trailingIcon = {
                                Icon(
                                    if(item.name == FilterChips.ALL.name){
                                        Icons.Default.FilterList
                                    }else Icons.Default.ArrowDropDown,
                                    contentDescription = null
                                )
                            }
                        )
                    }
                }
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



fun calculatePriceWithDiscount(price : Int, discount : Int) : Int {
    return price - (price * discount / 100)
}

enum class FilterChips{
    ALL,
    BATCH,
    PRICE,
    LANGUAGE,
    EXAM_TYPE
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