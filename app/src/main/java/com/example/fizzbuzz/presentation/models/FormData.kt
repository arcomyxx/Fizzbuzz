package com.example.fizzbuzz.presentation.models

import androidx.compose.runtime.State
import com.example.fizzbuzz.domain.models.Data
import com.example.fizzbuzz.presentation.viewmodels.ViewModel

data class FormData(
    var int1: State<String?>,
    var int2: State<String?>,
    var word1: State<String?>,
    var word2: State<String?>,
    var limit: State<String?>,
)

fun FormData.toDomain() = Data(
    int1 = checkIntField("entier 1", int1.value),
    int2 = checkIntField("entier 2", int2.value),
    word1 = checkStringField("mot 1", word1.value),
    word2 = checkStringField("mot 2", word2.value),
    limit = checkIntField("limite", limit.value),
)

private fun checkStringField(field: String, value: String?): String {
    if (value.isNullOrBlank()) throw Exception("$field manquant(e)")
    if (value.length > ViewModel.MAX_WORD) throw Exception("$field trop long(ue)")
    return value
}


private fun checkIntField(field: String, value: String?): Int {
    checkStringField(field, value)
    return value?.toIntOrNull() ?: throw Exception("$field n'est pas un nombre")
}