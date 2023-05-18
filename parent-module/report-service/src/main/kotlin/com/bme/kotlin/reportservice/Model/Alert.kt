package com.bme.kotlin.reportservice.Model

import com.bme.kotlin.reportservice.DTO.AlertDto
import jakarta.persistence.*

@Entity
class Alert (

    @ManyToOne
    @JoinColumn(name = "event_id")
    val event:Event,

    var ackMessageId: Int? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int=0,
){
    fun toDto():AlertDto{
        return AlertDto(this.id,this.event.id, this.ackMessageId)
    }
}