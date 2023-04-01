package com.youarelaunched.challenge.ui.screen.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.youarelaunched.challenge.data.repository.model.VendorEmptyItem
import com.youarelaunched.challenge.middle.R
import com.youarelaunched.challenge.ui.screen.state.VendorsScreenUiState
import com.youarelaunched.challenge.ui.screen.view.components.ChatsumerSnackbar
import com.youarelaunched.challenge.ui.screen.view.components.EmptyItem
import com.youarelaunched.challenge.ui.screen.view.components.SearchAppBar
import com.youarelaunched.challenge.ui.screen.view.components.VendorItem
import com.youarelaunched.challenge.ui.theme.VendorAppTheme

@Composable
fun VendorsRoute(
    viewModel: VendorsVM
) {
    val uiState by viewModel.uiState.collectAsState()

    VendorsScreen(uiState = uiState)
}

@Composable
fun VendorsScreen(
    uiState: VendorsScreenUiState
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = VendorAppTheme.colors.background,
        snackbarHost = { ChatsumerSnackbar(it) },
        topBar = { SearchAppBar() }
    ) { paddings ->
        if (!uiState.vendors.isNullOrEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .padding(paddings)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(
                    vertical = 24.dp,
                    horizontal = 16.dp
                )
            ) {
                items(uiState.vendors) { vendor ->
                    VendorItem(
                        vendor = vendor
                    )
                }

            }
        } else {
            EmptyItem(
                emptyItemsList = listOf(
                    VendorEmptyItem(
                        text = stringResource(id = R.string.empty_item_title),
                        style = VendorAppTheme.typography.h2,
                        color = VendorAppTheme.colors.textGreen,
                    ),
                    VendorEmptyItem(
                        text = stringResource(id = R.string.empty_item_description),
                        style = VendorAppTheme.typography.subtitle2,
                        color = VendorAppTheme.colors.textDark,
                        paddingValues = PaddingValues(vertical = 8.dp, horizontal = 28.dp)
                    )
                )
            )
        }
    }
}