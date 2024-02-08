package com.example.fizzbuzz.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fizzbuzz.presentation.models.FormData
import com.example.fizzbuzz.domain.usecases.FizzBuzzUseCase
import com.example.fizzbuzz.presentation.models.toDomain
import kotlinx.coroutines.launch

class ViewModel(
    private val fizzBuzzUseCase: FizzBuzzUseCase,
) : ViewModel() {
    companion object {
        const val MIN_LIMIT = 1
        const val MAX_LIMIT = 1000
        const val MAX_WORD = 10
    }

    private val _formData = mutableStateOf(
        FormData(
            int1 = mutableStateOf(null),
            int2 = mutableStateOf(null),
            word1 = mutableStateOf(null),
            word2 = mutableStateOf(null),
            limit = mutableStateOf(null),
        )
    )
    private val _results = mutableStateOf<List<String>>(emptyList())
    private val _error = mutableStateOf<String?>(null)

    val formData: State<FormData> = _formData
    val results: State<List<String>> = _results
    val error: State<String?> = _error

    fun onPlay() {
        viewModelScope.launch {
            try {
                val limitValue = _formData.value.limit.value
                if (limitValue != null && limitValue.toInt() < MIN_LIMIT || limitValue!!.toInt() > MAX_LIMIT)
                    throw Exception("limite doit être entre $MIN_LIMIT et $MAX_LIMIT")

                fizzBuzzUseCase(_formData.value.toDomain()).collect {
                    _results.value = it
                }
                _error.value = null
            } catch (e: Exception) {
                _results.value = emptyList()
                _error.value = e.message
            }
        }
    }

    fun onInt1Changed(newValue: String) {
        _formData.value = _formData.value.copy(int1 = mutableStateOf(newValue))
    }

    fun onInt2Changed(newValue: String) {
        _formData.value = _formData.value.copy(int2 = mutableStateOf(newValue))
    }

    fun onWord1Changed(newValue: String) {
        _formData.value = _formData.value.copy(word1 = mutableStateOf(newValue))
    }

    fun onWord2Changed(newValue: String) {
        _formData.value = _formData.value.copy(word2 = mutableStateOf(newValue))
    }

    fun onLimitChanged(newValue: String) {
        _formData.value = _formData.value.copy(limit = mutableStateOf(newValue))
    }
}