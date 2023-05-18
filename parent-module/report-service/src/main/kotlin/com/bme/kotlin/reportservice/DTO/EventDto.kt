package com.bme.kotlin.reportservice.DTO

import com.bme.kotlin.reportservice.Model.EventType
import com.bme.kotlin.reportservice.Model.Status

data class EventDto(
    val status: Status,
    val eventTypeId: Int,
    val id: Int,
    val alertDtoList: List<AlertDto>
)