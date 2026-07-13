package com.example.coaching_app.view.home

import androidx.lifecycle.ViewModel
import com.example.coaching_app.R
import kotlinx.coroutines.flow.MutableStateFlow

class HomeScreenViewModel : ViewModel(){

    fun getCardData() : List<CardData>{
        return HomeScreenData.data
    }
}


object HomeScreenData{

    val data = listOf(
        CardData(
            "Image",
            "Description",
            R.drawable.image1,
            "1 Jul 2026"
        ),
        CardData(
            "Image",
            "Description",
            R.drawable.image2,
            "1 Jul 2026"
        )
        ,
        CardData(
            "Image",
            "Description",
            R.drawable.image3,
            "1 Jul 2026"
        )
        ,
        CardData(
            "Image",
            "Description",
            R.drawable.image1,
            "1 Jul 2026"
        ),
        CardData(
            "Image",
            "Description",
            R.drawable.image2,
            "1 Jul 2026"
        )
        ,
        CardData(
            "Image",
            "Description",
            R.drawable.image3,
            "1 Jul 2026"
        )
        ,
        CardData(
            "Image",
            "Description",
            R.drawable.image1,
            "1 Jul 2026"
        )

    )
}

data class CardData(
    val title : String,
    val description : String,
    val image : Int,
    val time : String,
    val actual_price : String = "1000",
    val discount : String = "10"
)