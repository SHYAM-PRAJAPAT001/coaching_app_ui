package com.example.coaching_app.view.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.coaching_app.ui.theme.FontSize

@Composable

fun SubTitles(
    title : String
){
    Text(
        text = title,
        fontSize = FontSize.medium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(16.dp)
    )
}