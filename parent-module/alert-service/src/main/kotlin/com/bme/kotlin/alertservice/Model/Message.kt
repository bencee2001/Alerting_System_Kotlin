package com.bme.kotlin.alertservice.Model

import com.bme.kotlin.webclient.model.MessageDto
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Message (

    val alert_id: Int,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Int=0,
){
    fun toDto():MessageDto{
        return MessageDto(this.alert_id, this.id)
    }
}