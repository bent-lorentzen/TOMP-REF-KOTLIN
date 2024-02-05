package org.tomp.api.utils

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.mail.MailSendException
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty(value = ["spring.mail.username"], matchIfMissing = false)
class MailUtil {
    @Autowired
    var emailSender: JavaMailSender? = null
    fun sendSimpleMessage(from: String?, to: String?, subject: String?, text: String?) {
        val message = SimpleMailMessage()
        message.from = from
        message.setTo(to)
        message.subject = subject
        message.text = text
        try {
            emailSender!!.send(message)
        } catch (e: MailSendException) {
            log.error(e.message)
            log.error(text)
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(MailUtil::class.java)
    }
}
