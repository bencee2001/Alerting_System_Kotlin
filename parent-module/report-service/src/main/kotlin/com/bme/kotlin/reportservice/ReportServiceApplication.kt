package com.bme.kotlin.reportservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan


@SpringBootApplication
@ComponentScan(basePackages = ["com.bme.kotlin.reportservice","com.bme.kotlin.webclient"])
class ReportServiceApplication

fun main(args: Array<String>) {
	runApplication<ReportServiceApplication>(*args)
}
