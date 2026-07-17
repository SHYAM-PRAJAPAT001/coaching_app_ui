package com.example.coaching_app.view.home

import androidx.compose.material3.FilterChip

object  ChipField{

    val allField = listOf(
        FilterChips.ALL,
        FilterChips.BATCH,
        FilterChips.PRICE,
        FilterChips.LANGUAGE,
        FilterChips.EXAM_TYPE
    )

    val batches = listOf(
        "All",
        "Online",
        "Offline"
    )

    val priceRange = listOf(
        "All",
        "Below ₹2000",
        "₹2000 - ₹5000",
        "Above ₹5000"
    )

    val language = listOf(
        "All",
        "Hindi",
        "English",
        "Gujarati"
    )

    val examType = listOf(
        "All",
        "JEE",
        "NEET",
        "NEET-JEE"
    )

}