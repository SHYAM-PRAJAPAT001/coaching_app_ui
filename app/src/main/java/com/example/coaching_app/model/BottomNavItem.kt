package com.example.coaching_app.model

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem (
    val name : String ,
    val navigation : String ,
    val isSelected : ImageVector ,
    val unSelected : ImageVector
)