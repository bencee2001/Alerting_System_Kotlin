package com.bme.kotlin.reportservice.Servie

import com.bme.kotlin.reportservice.DTO.AlertDto
import com.bme.kotlin.reportservice.DTO.EventDto
import com.bme.kotlin.reportservice.Errors.NoSuchAlertException
import com.bme.kotlin.reportservice.Errors.NoSuchEventTypeException
import com.bme.kotlin.reportservice.Model.*
import com.bme.kotlin.reportservice.Repository.AlertRepo
import com.bme.kotlin.reportservice.Repository.EventRepo
import com.bme.kotlin.reportservice.Repository.EventTypeRepo
import com.bme.kotlin.webclient.extensions.unwrap
import com.bme.kotlin.webclient.model.MessageDto
import com.bme.kotlin.webclient.webclient.WebClientService
import org.springframework.stereotype.Service


@Service
class ReportService(
    private val alertRepo: AlertRepo,
    private val eventRepo: EventRepo,
    private val eventTypeRepo: EventTypeRepo,
    private val webClientService: WebClientService
) {
    /**
     * Finds the eventType. Gets the eventType serviceList.
     * Sends an alert for every service and saves the event and alerts.
     * @param eventTypeId id of the eventType that will be sent
     * @return list of the saved alerts in dto format
     */
    fun sendRequest(eventTypeId: Int): List<AlertDto> {
        val eventType = getEventType(eventTypeId)
        val event = eventRepo.save(Event(Status.NEW, eventType))
        return sendAlerts(event, eventType);
    }

    private fun getEventType(id: Int): EventType {
        return eventTypeRepo.lookForById(id) ?: throw NoSuchEventTypeException("No such EventType.")
    }

    private fun sendAlerts(event: Event, eventType: EventType): List<AlertDto> {
        var alertList = mutableListOf<Alert>()
        eventType.agentList.forEach {
            val alert = alertRepo.save(Alert(event))
            if (!webClientService.sendToAlert(it, alert.id))
                alertRepo.delete(alert)
            alertList.add(alert)
        }
        event.alertList = alertList
        eventRepo.save(event)
        return alertList.stream().map { it.toDto() }.toList();
    }

    /**
     * Processes the message that sent by the alertServices
     * @param messageDto sent message
     * @return answered event
     */
    fun answerAlert(messageDto: MessageDto): EventDto {
        var alert = alertRepo.findById(messageDto.alert_Id).unwrap()
            ?: throw NoSuchAlertException("No Alerto on id ${messageDto.alert_Id}")
        alert.messageId = messageDto.message_Id
        alertRepo.save(alert)
        return checkEvent(alert.event).toDto()
    }

    /**
     * Checks if every alert in a event answered
     * @param event event to be checked
     * @return refreshed event
     */
    private fun checkEvent(event: Event): Event {
        if(isAllAlertAnswered(event.alertList)){
            event.status=Status.CLOSED
        }else{
            event.status=Status.PARTIAL
        }
        return eventRepo.save(event)
    }

    private fun isAllAlertAnswered(alertList: List<Alert>): Boolean {
        alertList.forEach {
            it.messageId ?: return false
        }
        return true
    }

    /**
     * Gets the events from the database
     * @return list of event in dto format
     */
    fun getEventDtos(): List<EventDto> {
        return eventRepo.findAll().stream().map { it.toDto() }.toList()
    }

    /**
     * Gets the alerts from the database
     * @return list of alerts in dto format
     */
    fun getAlertDtos(): List<AlertDto> {
        return  alertRepo.findAll().stream().map { it.toDto() }.toList()
    }

    /**
     * Gets the eventTypes from the database
     * @return list of eventType
     */
    fun getEventTypes(): List<EventType> {
        return eventTypeRepo.findAll()

    }


}

