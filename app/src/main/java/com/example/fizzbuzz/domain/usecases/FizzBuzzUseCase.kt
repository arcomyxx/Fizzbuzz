package com.example.fizzbuzz.domain.usecases

import com.example.fizzbuzz.domain.models.FormData

class FizzBuzzUseCase {
    operator fun invoke(data: FormData): List<String> {
        val list = mutableListOf<String>()

        for (i in 1..data.limit) {
            val isInt1Multiple = i % data.int1 == 0
            val isInt2Multiple = i % data.int2 == 0

            val item = when {
                isInt1Multiple && isInt2Multiple -> data.word1 + data.word2
                isInt1Multiple -> data.word1
                isInt2Multiple -> data.word2
                else -> i.toString()
            }

            list.add(item)
        }
        return list
    }

}