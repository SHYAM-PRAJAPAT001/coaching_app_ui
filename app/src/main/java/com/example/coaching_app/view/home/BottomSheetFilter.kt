package com.example.coaching_app.view.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetFilters(
    onDismiss : () -> Unit,

){

    ModalBottomSheet(
        onDismissRequest = {onDismiss}
    ) {


    }
}