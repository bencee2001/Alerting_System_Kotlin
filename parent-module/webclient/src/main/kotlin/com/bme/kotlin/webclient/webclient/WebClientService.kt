package com.bme.kotlin.webclient.webclient

import com.bme.kotlin.webclient.model.MessageDto
import org.springframework.core.env.Environment
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono


//Generate URL by given String
@Component
class WebClientService(
    private val env: Environment,
){
    var clients: MutableMap<String, WebClient> = mutableMapOf<String, WebClient>()

    val PREFIX = "http://localhost:"

    /**
     * Sends the request to the chosen alert service
     * @param serviceName chosen servcie's name
     * @param alertId data to send
     * @return true if request retrun with 200 status code otherwise false
     */
    fun sendToAlert(serviceName:String, alertId: Int): Boolean {
        var isSuccessful = false;

        var res = getWebClientInstance(serviceName).post().uri("/alertService")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body(BodyInserters.fromValue(alertId))
            .retrieve()
            .toEntity(String::class.java)
            .block()

        isSuccessful = checkOkStatus(res)
        return isSuccessful
    }

    /**
     * Sends message to the report service
     * @param message
     */
    fun sendToReport(message:MessageDto){
        getWebClientInstance("report").post().uri("/answerReport")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body(BodyInserters.fromValue(message))
            .retrieve()
            .toEntity(String::class.java)
            .block()
    }

    private fun checkOkStatus(res: ResponseEntity<String>?):Boolean{
        if (res != null)
            if (res.statusCode == HttpStatusCode.valueOf(200))
                return true
        return false
    }

    private fun getWebClientInstance(name: String): WebClient {
        if(clients.containsKey(name))
            return clients[name]!!
        var urlString = getUrlString(name);
        val client = WebClient.builder().baseUrl(urlString).build()
        clients.put(name,client)
        return client;
    }

    private fun getUrlString(name: String): String {
        val port = env.getProperty("service.$name")
        return PREFIX+port;
    }

}