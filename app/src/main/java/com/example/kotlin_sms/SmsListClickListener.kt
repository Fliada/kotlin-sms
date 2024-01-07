package com.example.kotlin_sms

import com.example.kotlin_sms.data.item.SmsListItem

interface SmsListClickListener {
    fun onSmsListItemClick(entry: SmsListItem)
}