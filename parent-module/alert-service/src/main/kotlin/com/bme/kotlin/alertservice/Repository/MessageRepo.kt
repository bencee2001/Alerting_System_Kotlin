package com.bme.kotlin.alertservice.Repository

import com.bme.kotlin.alertservice.Model.Message
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MessageRepo : JpaRepository<Message, Int>{

    @Query("select distinct am.alert_id from Message am")
    fun getExistingAlertIds():Set<Int>;
}