package com.sledz.mobileapp.views.product_detail

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klim.tcharts.Colors
import com.klim.tcharts.entities.ChartData
import com.klim.tcharts.entities.ChartItem
import com.sledz.mobileapp.data.database.entities.ObservedProduct
import com.sledz.mobileapp.databinding.TchartLayoutBinding
import com.sledz.mobileapp.ui.theme.ProductListItem
import com.sledz.mobileapp.util.Resource
import com.sledz.mobileapp.util.TypeConverter
import java.util.*

@Composable
fun ProductDetailsScreen(
    navController: NavController,
    id: Long,
    productDetailsViewModel: ProductDetailsViewModel = hiltViewModel()
) {

    productDetailsViewModel.loadProduct(id)

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {

            Plot(productDetailsViewModel)

            TittleBar(productDetailsViewModel)

            Description(productDetailsViewModel)
        }
    }
}

@Composable
fun Plot(viewModel: ProductDetailsViewModel) {

    val itemDetails by viewModel.productDetails.observeAsState(Resource.Loading<ObservedProduct>())
    when(itemDetails) {
        is Resource.Success<ObservedProduct> -> {
            AndroidViewBinding(TchartLayoutBinding::inflate) {
                tchart.setData(TypeConverter.ObservedToChartData(itemDetails.data!!))
                tchart.setTitle("${itemDetails.data!!.name}")
                tchart.showLine("price", true)
            }
        }
        is Resource.Error<ObservedProduct> -> {
            Log.i("ProductDetailsVM", "how ?? ${itemDetails.message}")
        }
        else -> {

        }
    }
}



@Composable
fun TittleBar(viewModel: ProductDetailsViewModel) {

    val itemDetails by viewModel.productDetails.observeAsState(Resource.Loading<ObservedProduct>())
    when(itemDetails) {
        is Resource.Success<ObservedProduct> -> {
            Text(
                text = "Name: ${itemDetails.data!!.name}",
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .paddingFromBaseline(bottom = 24.dp)
            )
        }
        is Resource.Error<ObservedProduct> -> {
            Log.i("ProductDetailsVM", "how ?? ${itemDetails.message}")
        }
        else -> {

        }
    }

}

@Composable
fun Description(viewModel: ProductDetailsViewModel) {

    val itemDetails by viewModel.productDetails.observeAsState(Resource.Loading<ObservedProduct>())
    when(itemDetails) {
        is Resource.Success<ObservedProduct> -> {

        }
        is Resource.Error<ObservedProduct> -> {
            Log.i("ProductDetailsVM", "how ?? ${itemDetails.message}")
        }
        else -> {

        }
    }
}