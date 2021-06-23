package com.sledz.mobileapp.views.main

import android.app.Activity
import android.app.Application
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.*
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sledz.mobileapp.data.database.entities.ObservedProduct
import com.sledz.mobileapp.data.models.AuthToken
import com.sledz.mobileapp.data.models.Product
import com.sledz.mobileapp.data.models.defaultListOfProducts
import com.sledz.mobileapp.ui.theme.ProductListItem
import com.sledz.mobileapp.util.Resource
import com.sledz.mobileapp.util.TypeConverter
import com.sledz.mobileapp.views.Spacing

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()) {

    mainViewModel.loadSubscribed()
    mainViewModel.loadCategories()

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            SearchInput()

            Spacing(value = 8.dp)

            CategoryDropdown()

            Spacing(value = 8.dp)

            SearchButton(navController)

            Spacing(value = 8.dp)

            ObservedSection(navController, mainViewModel)

        }
    }
}

@Composable
private fun ObservedSection(navController: NavController, viewModel: MainViewModel) {

    val itemsObserved by viewModel.subscribedProducts.observeAsState(Resource.Loading<List<ObservedProduct>>())
    when(itemsObserved) {
        is Resource.Success<List<ObservedProduct>> -> {
            val items = itemsObserved.data!!.map { TypeConverter.ObservedToProduct(it) }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                item {
                    Text(
                        text = "Obserwowane produkty",
                        style = MaterialTheme.typography.h4
                    )
                }
                items(items) { prod ->
                    ProductListItem(navController, prod)
                }
            }
        }
        is Resource.Error<List<ObservedProduct>> -> {
            Log.i("MainScreen", "how ?? ${itemsObserved.message}")
        }
        else -> {

        }
    }

}

@Composable
fun CategoryDropdown(viewModel: MainViewModel = hiltViewModel()) {
    var expanded by remember {
        mutableStateOf(false)
    }

    val categories by viewModel.categories.observeAsState(listOf<String>(""))
    var selectedIndex by remember { mutableStateOf(0) }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(color = MaterialTheme.colors.primary, shape = RoundedCornerShape(20.dp))
            .clickable(onClick = { expanded = true })
    ) {
        Text(
            categories[selectedIndex],
            style = MaterialTheme.typography.body2,
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterStart)
                .padding(start = 16.dp)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            categories.forEachIndexed { index, element ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    viewModel.onSelectCategory(index)
                    expanded = false
                }) {
                    Text(text = element)
                }
            }
        }
    }
}

@Composable
fun SearchButton(navController: NavController, viewModel: MainViewModel = hiltViewModel()) {
    Button(onClick = { navController.navigate("search/${viewModel.search.value} ${viewModel.selectedCategory.value}") }) {
        Text("Szukaj produktu")
    }
}

@Composable
private fun SearchInput(viewModel: MainViewModel = hiltViewModel()) {
    val search by viewModel.search.observeAsState("")

    OutlinedTextField(
        value = search,
        onValueChange = {
            viewModel.onSearchUpdate(it)
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
    MainScreen(rememberNavController())
}