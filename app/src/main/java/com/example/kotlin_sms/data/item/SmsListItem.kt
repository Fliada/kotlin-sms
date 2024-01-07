package com.example.kotlin_sms.data.item

import java.io.Serializable

data class SmsListItem(
    val address: String,
    val messages: List<MessageListItem>
): Serializable