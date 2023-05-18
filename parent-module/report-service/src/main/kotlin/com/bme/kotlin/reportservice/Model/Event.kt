package com.bme.kotlin.reportservice.Model

import com.bme.kotlin.reportservice.DTO.EventDto
import jakarta.persistence.*
import lombok.AllArgsConstructor

@Entity
class Event (

    var status: Status,

    @OneToOne
    val eventType: EventType,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int=0,
){
    @OneToMany(mappedBy = "event")
    var alertList:List<Alert> = mutableListOf()

    fun toDto():EventDto{
        return EventDto(
            this.status,
            this.eventType.id,
            this.id,
            this.alertList.stream().map { it.toDto() }.toList()
        )
    }
}
