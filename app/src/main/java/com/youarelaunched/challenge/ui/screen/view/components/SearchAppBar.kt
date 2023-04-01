package com.youarelaunched.challenge.ui.screen.view.components

import android.os.Looper
import android.os.Handler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.youarelaunched.challenge.middle.R
import com.youarelaunched.challenge.ui.theme.Shapes
import com.youarelaunched.challenge.ui.theme.VendorAppTheme

private const val DELAY_MILLIS = 500L
private const val LETTERS_TO_AUTO_SEARCH = 2

@Composable
fun SearchAppBar(
    modifier: Modifier = Modifier, paddingValues: PaddingValues = PaddingValues(
        start = 16.dp, top = 24.dp, end = 16.dp
    ), clickOnSearch: (String) -> Unit
) {
    var textSearchPlaceholder by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .shadow(
                elevation = 14.dp,
                ambientColor = VendorAppTheme.colors.shadowAmbientColor,
                shape = Shapes.medium
            )
            .background(color = VendorAppTheme.colors.buttonUnselected, shape = Shapes.medium),
        value = textSearchPlaceholder,
        onValueChange = {
            textSearchPlaceholder = it
            if (textSearchPlaceholder.length > LETTERS_TO_AUTO_SEARCH || textSearchPlaceholder.isBlank()) {
                Handler(Looper.getMainLooper()).postDelayed(
                    { clickOnSearch(textSearchPlaceholder) }, DELAY_MILLIS
                )
            }
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_placeholder),
                style = VendorAppTheme.typography.subtitle2
            )
        },
        trailingIcon = {
            IconButton(onClick = { clickOnSearch(textSearchPlaceholder) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search), contentDescription = null
                )
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = VendorAppTheme.colors.buttonUnselected,
            unfocusedBorderColor = VendorAppTheme.colors.buttonUnselected,
            trailingIconColor = VendorAppTheme.colors.text,
            textColor = VendorAppTheme.colors.text,
            placeholderColor = VendorAppTheme.colors.text,
            cursorColor = VendorAppTheme.colors.text
        ),
        singleLine = true,
        textStyle = VendorAppTheme.typography.subtitle2
    )
}