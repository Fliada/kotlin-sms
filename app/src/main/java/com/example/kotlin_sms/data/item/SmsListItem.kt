package com.example.kotlin_sms.data.item

data class SmsListItem(
    val address: String,
    val messages: List<MessageListItem>
)