package com.example.simpleenglishserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SimpleEnglishServerApplication

fun main(args: Array<String>) {
    runApplication<SimpleEnglishServerApplication>(*args)
}
