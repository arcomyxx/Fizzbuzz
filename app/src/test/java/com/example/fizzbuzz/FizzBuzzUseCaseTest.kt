package com.example.fizzbuzz

import com.example.fizzbuzz.domain.models.FormData
import com.example.fizzbuzz.domain.usecases.FizzBuzzUseCase
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class FizzBuzzUseCaseTest {

    @ParameterizedTest
    @MethodSource("testData")
    fun testFizzBuzz(
        int1: Int, int2: Int, word1: String, word2: String, limit: Int, expectedResults: String
    ) {
        val useCase = FizzBuzzUseCase()
        val results = useCase(FormData(int1, int2, word1, word2, limit))

        assertEquals(expectedResults, results.joinToString(","))
    }

    companion object {
        @JvmStatic
        fun testData(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    3,
                    5,
                    "Fizz",
                    "Buzz",
                    15,
                    "1,2,Fizz,4,Buzz,Fizz,7,8,Fizz,Buzz,11,Fizz,13,14,FizzBuzz"
                ),
                Arguments.of(2, 3, "Foo", "Bar", 10, "1,Foo,Bar,Foo,5,FooBar,7,Foo,Bar,Foo")
            )
        }
    }
}