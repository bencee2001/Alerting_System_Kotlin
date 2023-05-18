package com.bme.kotlin.reportservice.Repository

import com.bme.kotlin.reportservice.Model.Alert
import com.bme.kotlin.reportservice.Model.EventType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface AlertRepo:JpaRepository<Alert,Int>{

    @Query("select et from Alert et where et.id = :id")
    fun lookForById(@Param("id") alertId:Int): EventType?;
}