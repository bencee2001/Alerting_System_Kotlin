package com.bme.kotlin.alertservice.Web

import com.bme.kotlin.alertservice.Model.Message
import com.bme.kotlin.alertservice.Service.AlertService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/alertApi")
class AlertController(
    private val alertService: AlertService
) {

    @PostMapping("/alertService")
    fun alertPoint(@RequestBody alertId: Int):ResponseEntity<Message>{
        return ResponseEntity.ok(alertService.save(alertId))
    }

    @PostMapping("/sendBack/{messageId}")
    fun sendBackAnswer(@PathVariable messageId: Int):ResponseEntity<String>{
        alertService.send(messageId)
        return ResponseEntity.ok("ok $messageId")
    }

    @GetMapping("/messages")
    fun getMessages():List<Message>{
        return alertService.getMessages()
    }
}