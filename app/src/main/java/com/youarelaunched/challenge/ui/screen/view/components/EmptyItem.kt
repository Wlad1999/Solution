package com.youarelaunched.challenge.ui.screen.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.youarelaunched.challenge.data.repository.model.VendorEmptyItem


@Composable
fun EmptyItem(
    modifier: Modifier = Modifier,
    emptyItemsList: List<VendorEmptyItem>,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(emptyItemsList) { emptyItem ->
            Text(
                modifier= modifier.padding(emptyItem.paddingValues),
                text = emptyItem.text,
                style = emptyItem.style,
                textAlign = TextAlign.Center,
                color = emptyItem.color
            )
        }
    }
}