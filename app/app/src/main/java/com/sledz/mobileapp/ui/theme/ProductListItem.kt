package com.sledz.mobileapp.ui.theme

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sledz.mobileapp.data.models.Product
import com.sledz.mobileapp.data.models.defaultListOfProducts
import okhttp3.internal.format

@Composable
fun ProductListItem(
    navController: NavController,
    product: Product
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { navController.navigate("productDetails/${product.Id}") })
    ) {
        ProductImage(product)

        Column {
            TitleAndDescription(product)

            Divider()
        }
    }
    Log.i("ProductListItem", "Created product (List Element) id: ${product.Id}")
}

@Composable
private fun TitleAndDescription(product: Product) {
    Column {
        Text(
            text = product.title,
            style = MaterialTheme.typography.h2,
            modifier = Modifier
                .paddingFromBaseline(top = 24.dp)
        )

        Text(
            text = "${product.currentPrice} zł",
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .paddingFromBaseline(bottom = 24.dp)
        )
    }
}

@Composable
private fun ProductImage(product: Product) {
    Image(
        painter = painterResource(id = product.imageRes),
        contentDescription = "Obraz ${product.title}",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(64.dp)
            .clip(MaterialTheme.shapes.small)
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
private fun LoginScreenPreview() {
    MobileAppTheme {
        Surface(
            color = MaterialTheme.colors.background,
        ) {
            ProductListItem(rememberNavController(), defaultListOfProducts.first())
        }
    }
}