package com.sledz.mobileapp.views.product_detail

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import com.sledz.mobileapp.views.Spacing
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

            TittleBar(productDetailsViewModel)

            Divider(color = MaterialTheme.colors.primary, thickness = 2.dp)

            Box(Modifier.height(360.dp) ) {
                Plot(productDetailsViewModel)
            }

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

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .padding(8.dp)
                    .height(40.dp)
                    .fillMaxWidth()) {

                itemDetails.data?.let {
                    Text(
                        text = it.name,
                        fontSize = 32.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }

                val buttonText = viewModel.buttonText.observeAsState(initial = "")

                Button(onClick = {viewModel.subscribeButtonOnClick()},  modifier = Modifier.fillMaxHeight()) {
                    Text(text = buttonText.value)
                }
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
fun Description(viewModel: ProductDetailsViewModel) {

    val itemDetails by viewModel.productDetails.observeAsState(Resource.Loading<ObservedProduct>())
    when(itemDetails) {
        is Resource.Success<ObservedProduct> -> {
            Text( text = itemDetails.data!!.description)
        }
        is Resource.Error<ObservedProduct> -> {
            Log.i("ProductDetailsVM", "how ?? ${itemDetails.message}")
        }
        else -> {

        }
    }
}