package com.example.fizzbuzz.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.fizzbuzz.domain.models.FormData
import com.example.fizzbuzz.domain.usecases.FizzBuzzUseCase

class ViewModel(
    private val fizzBuzzUseCase: FizzBuzzUseCase,
) : ViewModel() {
    private companion object {
        const val MIN_LIMIT = 1
        const val MAX_LIMIT = 1000
        const val MAX_WORD = 10
    }

    private val _int1 = mutableStateOf<String?>(null)
    private val _int2 = mutableStateOf<String?>(null)
    private val _word1 = mutableStateOf<String?>(null)
    private val _word2 = mutableStateOf<String?>(null)
    private val _limit = mutableStateOf<String?>(null)
    private val _results = mutableStateOf<List<String>>(emptyList())
    private val _error = mutableStateOf<String?>(null)

    val int1: State<String?> = _int1
    val int2: State<String?> = _int2
    val word1: State<String?> = _word1
    val word2: State<String?> = _word2
    val limit: State<String?> = _limit
    val results: State<List<String>> = _results
    val error: State<String?> = _error

    fun onPlay() {
        try {
            if (limit.value!!.toInt() < MIN_LIMIT || limit.value!!.toInt() > MAX_LIMIT)
                throw Exception("limite doit être entre $MIN_LIMIT et $MAX_LIMIT")

            _results.value = fizzBuzzUseCase(
                FormData(
                    int1 = checkIntField("entier 1", int1.value),
                    int2 = checkIntField("entier 2", int2.value),
                    word1 = checkStringField("mot 1", word1.value),
                    word2 = checkStringField("mot 2", word2.value),
                    limit = checkIntField("limite", limit.value),
                )
            )
            _error.value = null
        } catch (e: Exception) {
            _results.value = emptyList()
            _error.value = e.message
        }
    }

    private fun checkStringField(field: String, value: String?): String {
        if (value.isNullOrBlank()) throw Exception("$field manquant(e)")
        if (value.length > MAX_WORD) throw Exception("$field trop long(ue)")
        return value
    }


    private fun checkIntField(field: String, value: String?): Int {
        checkStringField(field, value)
        return value?.toIntOrNull() ?: throw Exception("$field n'est pas un nombre")
    }

    fun onInt1Changed(newValue: String) {
        _int1.value = newValue
    }

    fun onInt2Changed(newValue: String) {
        _int2.value = newValue
    }

    fun onWord1Changed(newValue: String) {
        _word1.value = newValue
    }

    fun onWord2Changed(newValue: String) {
        _word2.value = newValue
    }

    fun onLimitChanged(newValue: String) {
        _limit.value = newValue
    }
}