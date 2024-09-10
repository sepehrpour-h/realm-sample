package com.sepehrpour.realmsample.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sepehrpour.realmsample.domain.viewmodels.CounterViewModel


@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel: CounterViewModel = viewModel()
    val counterResults = viewModel.getCounter().firstOrNull()
    var counterValue by remember { mutableIntStateOf(counterResults?.value ?: 0) }


    LaunchedEffect(counterResults) {
        counterValue = counterResults?.value ?: 0
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "مقدار فعلی: $counterValue", style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                viewModel.increaseCounter()
                counterValue += 1
            }) {
                Text("افزایش")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                if (counterValue > 0) {
                    viewModel.decreaseCounter()
                    counterValue -= 1
                }
            }) {
                Text("کاهش")
            }
        }
    }
}