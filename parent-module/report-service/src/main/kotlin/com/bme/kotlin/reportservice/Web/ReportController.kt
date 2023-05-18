package com.bme.kotlin.reportservice.Web

import com.bme.kotlin.reportservice.DTO.AlertDto
import com.bme.kotlin.reportservice.DTO.EventDto
import com.bme.kotlin.reportservice.Model.EventType
import com.bme.kotlin.reportservice.Servie.ReportService
import com.bme.kotlin.webclient.model.MessageDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/reportApi")
class ReportController(
    private val reportService: ReportService
) {

    @PostMapping("/send")
    fun sendRequest(eventTypeId:Int):ResponseEntity<List<AlertDto>>{
        return ResponseEntity.ok(reportService.sendRequest(eventTypeId))
    }

    @PostMapping("/answerReport")
    fun answerPoint(@RequestBody messageDto: MessageDto):ResponseEntity<EventDto>{
        return ResponseEntity.ok(reportService.answerAlert(messageDto))
    }

    @GetMapping("/events")
    fun getEvents():List<EventDto>{
        return reportService.getEventDtos()
    }

    @GetMapping("/alerts")
    fun getAlerts():List<AlertDto>{
        return reportService.getAlertDtos()
    }

    @GetMapping("/eventTypes")
    fun getEventTypes():List<EventType>{
        return reportService.getEventTypes()
    }
}