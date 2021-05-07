package com.sledz.mobileapp.views.main

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sledz.mobileapp.defaultListOfProducts
import com.sledz.mobileapp.ui.theme.MobileAppTheme
import com.sledz.mobileapp.ui.theme.ProductListItem
import com.sledz.mobileapp.views.SledzEmailInput
import com.sledz.mobileapp.views.SledzPasswordInput
import com.sledz.mobileapp.views.SledzSecondaryButton
import com.sledz.mobileapp.views.Spacing

@Composable
fun MainScreen() {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            Spacing(40.dp)

            SearchInput()

            ObservedSection()
        }
    }
}

@Composable
private fun ObservedSection() {
    Text(
        text = "Obserwowane produkty",
        style = MaterialTheme.typography.h1,
        modifier = Modifier
            .paddingFromBaseline(32.dp)
    )

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        items(defaultListOfProducts) { prod ->
            ProductListItem(product = prod)
        }

    }
}

@Composable
private fun SearchInput() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        label = {
            Text(text = "Szukaj produktu")
        },
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = null,
                modifier = Modifier
                    .size(18.dp)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
    )
}


@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun MainScreenPreview() {
    MobileAppTheme {
        MainScreen()
    }
}