package com.bme.kotlin.reportservice.Model

import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class EventType (
    @Id
    val id: Int,

    @ElementCollection
    @Column(name = "agent_name")
    val agentList: List<String>
)


