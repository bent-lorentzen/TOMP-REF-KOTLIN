package org.tomp.api.controllers

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.MessagingException
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller

@Controller
class WebsocketController {
    @Autowired
    var mapper: ObjectMapper? = null

    @Autowired
    var simpMessagingTemplate: SimpMessagingTemplate? = null
    fun sendMessage(message: String, o: Any?) {
        try {
            if (o == null) {
                simpMessagingTemplate!!.convertAndSend("/topic/backend", message + "\r\n")
            } else {
                simpMessagingTemplate!!.convertAndSend(
                    "/topic/backend",
                    """
                        $message
                        ${mapper!!.writeValueAsString(o)}
                        
                        """.trimIndent()
                )
            }
        } catch (e: MessagingException) {
            e.printStackTrace()
        } catch (e: JsonProcessingException) {
            e.printStackTrace()
        }
    }
}
