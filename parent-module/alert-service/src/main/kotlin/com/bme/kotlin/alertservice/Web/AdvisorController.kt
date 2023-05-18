package com.bme.kotlin.alertservice.Web

import com.bme.kotlin.alertservice.Errors.NoSuchMessageException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class AdvisorController {

    @ExceptionHandler(NoSuchMessageException::class)
    fun handleError(e:RuntimeException, request: WebRequest): ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
    }


}