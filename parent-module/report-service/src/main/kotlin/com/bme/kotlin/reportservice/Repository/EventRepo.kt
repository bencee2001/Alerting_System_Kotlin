package com.bme.kotlin.reportservice.Repository

import com.bme.kotlin.reportservice.Model.Event
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EventRepo:JpaRepository<Event, Int>