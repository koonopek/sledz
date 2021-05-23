package com.sledz.mobileapp.views.search

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sledz.mobileapp.data.models.defaultListOfProducts
import com.sledz.mobileapp.ui.theme.ProductListItem
import com.sledz.mobileapp.views.Spacing

@Composable
fun SearchScreen(){
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
//            SearchInput()

            Spacing(value = 8.dp)

            CategoryDropdown()

            Spacing(value = 8.dp)

//            FoundSection()
        }
    }
}

@Composable
fun FoundSection() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            Text(
                text = "Znalezione produkty",
                style = MaterialTheme.typography.h4
            )
        }
        items(defaultListOfProducts) { prod ->
            ProductListItem(product = prod)
        }
    }
}


@Composable
private fun SearchInput() {
    var textState by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        value = textState,
        onValueChange = {
            textState = it
        },
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
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun CategoryDropdown() {
    var expanded by remember {
        mutableStateOf(false)
    }
    val items = listOf("A", "B", "C", "D", "E", "F")
    var selectedIndex by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(18.dp)
            .background(color = MaterialTheme.colors.primary,shape = RoundedCornerShape(25.dp))
    ) {
        Text(
            items[selectedIndex],
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { expanded = true })
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            items.forEachIndexed { index, element ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                }) {
                    Text(text = element)
                }
            }
        }
    }
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
    SearchScreen()
}