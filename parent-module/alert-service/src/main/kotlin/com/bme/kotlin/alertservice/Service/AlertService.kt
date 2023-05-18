package com.bme.kotlin.alertservice.Service

import com.bme.kotlin.alertservice.Errors.AlreadyExistsException
import com.bme.kotlin.alertservice.Errors.NoSuchMessageException
import com.bme.kotlin.alertservice.Model.Message
import com.bme.kotlin.alertservice.Repository.AlertMessageRepo
import com.bme.kotlin.webclient.extensions.unwrap
import com.bme.kotlin.webclient.webclient.WebClientService
import org.springframework.stereotype.Service

@Service
class AlertService(
    private val alertMessageRepo: AlertMessageRepo,
    private val webClientService: WebClientService
) {
    /**
     * Saves the alertId into to a Message
     * @param alertId the alertId that will be saved
     * @throws AlreadyExistsException if message with the alertId exists this is thrown
     * @return new Message with alertId
     */
    fun save(alertId: Int): Message {
        if(alertMessageRepo.getExistingAlertIds().contains(alertId))
            throw AlreadyExistsException("This alert id($alertId) is already exists")
        return alertMessageRepo.save(Message(alertId))
    }

    /**
     * Gets the message if exists then it sends the message to the report service
     * @throws NoSuchMessageException thrown if message not exists
     * @param messageId the id of the message
     */
    fun send(messageId: Int) {
        val alertMessage = alertMessageRepo.findById(messageId).unwrap() ?: throw NoSuchMessageException()
        webClientService.sendToReport(alertMessage.toDto())
    }

    /**
     * Gets all the messages from the database
     * @return list of messages
     */
    fun getMessages(): List<Message> {
        return alertMessageRepo.findAll()
    }


}