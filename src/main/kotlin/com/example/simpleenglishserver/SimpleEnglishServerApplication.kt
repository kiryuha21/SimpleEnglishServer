package com.example.simpleenglishserver

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableEncryptableProperties
class SimpleEnglishServerApplication

fun main(args: Array<String>) {
    runApplication<SimpleEnglishServerApplication>(*args)
}
