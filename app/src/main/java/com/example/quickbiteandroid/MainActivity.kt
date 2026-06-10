package com.example.quickbiteandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                QuickBiteApp()
            }
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
fun ScreenLayout(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            content()
        }
    }
}

@Composable
fun BackButton(onBack: () -> Unit) {
    OutlinedButton(
        onClick = onBack,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Back")
    }
}

@Composable
fun FoodItemCard(
    foodName: String,
    price: String,
    onFoodDetails: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = foodName,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = price,
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onFoodDetails,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Food Details")
            }
        }
    }
}

@Composable
fun HomeScreen(onViewMenu: () -> Unit) {
    ScreenLayout(title = "QuickBite") {
        Text(
            text = "Fast food delivery app",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(20.dp)
            ) {
                Text(
                    text = "Featured Restaurant",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Campus Burger",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onViewMenu,
                    modifier = Modifier.fillMaxWidth()
                ) {
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
    ScreenLayout(title = "Menu") {
        FoodItemCard(
            foodName = "Cheeseburger",
            price = "$8.99",
            onFoodDetails = onFoodDetails
        )

        Spacer(modifier = Modifier.height(20.dp))

        BackButton(onBack = onBack)
    }
}

@Composable
fun FoodDetailsScreen(
    onAddToCart: () -> Unit,
    onBack: () -> Unit
) {
    ScreenLayout(title = "Food Details") {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.burger),
                contentDescription = "Burger image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Cheeseburger",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Fresh burger with cheese",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Price: $8.99",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onAddToCart,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add to Cart")
        }

        Spacer(modifier = Modifier.height(10.dp))

        BackButton(onBack = onBack)
    }
}

@Composable
fun CartScreen(
    onCheckout: () -> Unit,
    onBack: () -> Unit
) {
    ScreenLayout(title = "Cart") {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Cheeseburger")
                    Text("$8.99")
                }

                Divider(modifier = Modifier.padding(vertical = 12.dp))

                Text(
                    text = "Total: $8.99",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onCheckout,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Checkout")
        }

        Spacer(modifier = Modifier.height(10.dp))

        BackButton(onBack = onBack)
    }
}

@Composable
fun CheckoutScreen(
    onBackHome: () -> Unit
) {
    ScreenLayout(title = "Checkout") {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Payment and order summary",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))

        Text("Order: Cheeseburger")
        Text("Total: $8.99")
        Spacer(modifier = Modifier.height(12.dp))

        Text("Order: Cheeseburger")
        Text("Total: $8.99")
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = onBackHome,
modifier = Modifier.fillMaxWidth()
) {
    Text("Back Home")
}
}
}