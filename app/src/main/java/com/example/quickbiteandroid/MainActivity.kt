package com.example.quickbiteandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            QuickBiteApp()
        }
    }
}

@Composable
fun QuickBiteApp() {
    var screen by remember { mutableStateOf("home") }

    when (screen) {
        "home" -> HomeScreen(onViewMenu = { screen = "menu" })
        "menu" -> MenuScreen(
            onFoodDetails = { screen = "details" },
            onBack = { screen = "home" }
        )
        "details" -> FoodDetailsScreen(
            onAddToCart = { screen = "cart" },
            onBack = { screen = "menu" }
        )
        "cart" -> CartScreen(
            onCheckout = { screen = "checkout" },
            onBack = { screen = "details" }
        )
        "checkout" -> CheckoutScreen(
            onBackHome = { screen = "home" }
        )
    }
}

@Composable
fun HomeScreen(onViewMenu: () -> Unit) {
    Column(modifier = Modifier.padding(20.dp)) {

        Text(
            text = "QuickBite",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text("Fast food delivery app")

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(20.dp)
        ) {
            Column {
                Text("Featured Restaurant")
                Text("Campus Burger")

                Spacer(modifier = Modifier.height(10.dp))

                Button(onClick = onViewMenu) {
                    Text("View Menu")
                }
            }
        }
    }
}

@Composable
fun MenuScreen(
    onFoodDetails: () -> Unit,
    onBack: () -> Unit
) {

    Column(modifier = Modifier.padding(20.dp)) {

        Text(
            text = "Menu",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Cheeseburger")
            Text("$8.99")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = onFoodDetails) {
            Text("Food Details")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = onBack) {
            Text("Back")
        }
    }
}

@Composable
fun FoodDetailsScreen(
    onAddToCart: () -> Unit,
    onBack: () -> Unit
) {

    Column(modifier = Modifier.padding(20.dp)) {

        Text(
            text = "Food Details",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(MaterialTheme.colorScheme.secondaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Text("Food Image")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text("Cheeseburger")
        Text("Fresh burger with cheese")
        Text("Price: $8.99")

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = onAddToCart) {
            Text("Add to Cart")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = onBack) {
            Text("Back")
        }
    }
}

@Composable
fun CartScreen(
    onCheckout: () -> Unit,
    onBack: () -> Unit
) {

    Column(modifier = Modifier.padding(20.dp)) {

        Text(
            text = "Cart",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Cheeseburger")
            Text("$8.99")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text("Total: $8.99")

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = onCheckout) {
            Text("Checkout")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = onBack) {
            Text("Back")
        }
    }
}

@Composable
fun CheckoutScreen(
    onBackHome: () -> Unit
) {

    Column(modifier = Modifier.padding(20.dp)) {

        Text(
            text = "Checkout",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text("Payment and order summary")

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = onBackHome) {
            Text("Back Home")
        }
    }
}