package com.bme.kotlin.reportservice.Web

import com.bme.kotlin.reportservice.Errors.NoSuchAlertException
import com.bme.kotlin.reportservice.Errors.NoSuchEventTypeException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class AdvisorController {

    @ExceptionHandler(NoSuchAlertException::class, NoSuchEventTypeException::class)
    fun handleError(e:RuntimeException, request:WebRequest):ResponseEntity<String>{
        return ResponseEntity(e.message,HttpStatus.BAD_REQUEST)
    }
}