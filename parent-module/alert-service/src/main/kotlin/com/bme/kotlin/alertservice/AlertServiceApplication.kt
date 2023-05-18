package com.bme.kotlin.alertservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.bme.kotlin.alertservice","com.bme.kotlin.webclient"])
class AlertServiceApplication

fun main(args: Array<String>) {
	runApplication<AlertServiceApplication>(*args)
}
