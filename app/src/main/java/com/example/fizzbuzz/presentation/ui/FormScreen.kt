package com.example.fizzbuzz.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fizzbuzz.presentation.theme.FizzBuzzTheme
import com.example.fizzbuzz.presentation.theme.typography
import com.example.fizzbuzz.presentation.viewmodels.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fizzbuzz.R
import com.example.fizzbuzz.domain.usecases.FizzBuzzUseCase
import com.example.fizzbuzz.presentation.navigation.Screen


@Composable
fun FormScreen(
    modifier: Modifier = Modifier,
    viewModel: ViewModel,
    navController: NavHostController = rememberNavController(),
) {
    val int1 by remember { viewModel.int1 }
    val int2 by remember { viewModel.int2 }
    val word1 by remember { viewModel.word1 }
    val word2 by remember { viewModel.word2 }
    val limit by remember { viewModel.limit }
    val error by remember { viewModel.error }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(30.dp),
            style = typography.titleLarge,
            text = stringResource(R.string.app_name)
        )

        error?.let {
            Text(
                modifier = Modifier.testTag("errorField"),
                text = it,
                color = Color.Red
            )
        }

        TextFieldWithLabel(
            label = stringResource(R.string.int_1),
            value = int1,
            onValueChange = { viewModel.onInt1Changed(it) },
            onlyNumbers = true,
            testTag = "int1TextField",
        )

        TextFieldWithLabel(
            label = stringResource(R.string.int_2),
            value = int2,
            onValueChange = { viewModel.onInt2Changed(it) },
            onlyNumbers = true,
            testTag = "int2TextField",
        )

        TextFieldWithLabel(
            label = stringResource(R.string.word_1),
            value = word1,
            onValueChange = { viewModel.onWord1Changed(it) },
            testTag = "word1TextField",
        )

        TextFieldWithLabel(
            label = stringResource(R.string.word_2),
            value = word2,
            onValueChange = { viewModel.onWord2Changed(it) },
            testTag = "word2TextField",
        )

        TextFieldWithLabel(
            label = stringResource(R.string.limit),
            value = limit,
            onValueChange = { viewModel.onLimitChanged(it) },
            onlyNumbers = true,
            testTag = "limitTextField",
        )

        Button(
            modifier = Modifier.padding(10.dp).testTag("playButton"),
            onClick = {
                viewModel.onPlay()
                if (error == null) {
                    navController.navigate(Screen.RESULTS.name)
                }
            },
        ) {
            Text(text = stringResource(R.string.play))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithLabel(
    modifier: Modifier = Modifier,
    label: String,
    value: String?,
    onValueChange: (String) -> Unit,
    onlyNumbers: Boolean = false,
    testTag: String = "",
) {
    TextField(
        modifier = modifier
            .padding(5.dp)
            .testTag(testTag),
        value = value ?: "",
        onValueChange = onValueChange,
        label = { Text(text = "$label :") },
        keyboardOptions = if (onlyNumbers) KeyboardOptions(keyboardType = KeyboardType.Number) else KeyboardOptions.Default
    )
}

@Preview(showBackground = true)
@Composable
fun FormPreview() {
    FizzBuzzTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            FormScreen(viewModel = ViewModel(FizzBuzzUseCase()))
        }
    }
}