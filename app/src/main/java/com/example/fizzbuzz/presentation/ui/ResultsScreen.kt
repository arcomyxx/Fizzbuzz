package com.example.fizzbuzz.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fizzbuzz.domain.usecases.FizzBuzzUseCase
import com.example.fizzbuzz.presentation.theme.FizzBuzzTheme
import com.example.fizzbuzz.presentation.viewmodels.ViewModel

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    viewModel: ViewModel,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(viewModel.results.value) { item ->
            Text(
                modifier = Modifier.padding(2.dp),
                text = item,
                fontSize = 25.sp,
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ListPreview() {
    FizzBuzzTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ListScreen(viewModel = ViewModel(FizzBuzzUseCase()))
        }
    }
}