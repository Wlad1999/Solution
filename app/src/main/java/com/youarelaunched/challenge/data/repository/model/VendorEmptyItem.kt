package com.youarelaunched.challenge.data.repository.model

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

data class VendorEmptyItem(
    val text: String,
    val style: TextStyle,
    val color: Color,
    val paddingValues: PaddingValues = PaddingValues()
)
