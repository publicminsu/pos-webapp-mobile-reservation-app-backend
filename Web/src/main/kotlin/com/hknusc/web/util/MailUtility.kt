package com.hknusc.web.util

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

@Component
class MailUtility(private val mailSender: JavaMailSender) {
    fun send(subject: String, text: String, to: String) {
        val mailMessage = SimpleMailMessage()
        mailMessage.subject = subject
        mailMessage.text = text
        mailMessage.setTo(to)

        mailSender.send(mailMessage)
    }
}