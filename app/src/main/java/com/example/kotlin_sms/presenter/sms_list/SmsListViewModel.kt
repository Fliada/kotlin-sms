package com.example.kotlin_sms.presenter.sms_list

import android.content.ContentResolver
import android.os.Build
import android.provider.Telephony
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_sms.data.item.MessageListItem
import com.example.kotlin_sms.data.item.SmsListItem
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

class SmsListViewModel: ViewModel() {

    private val _chatMessageItems = MutableLiveData<List<SmsListItem>>()
    val chatMessageEntries: LiveData<List<SmsListItem>>
        get() = _chatMessageItems


    fun loadSmsMessages(contentResolver: ContentResolver) {
        val cursor = contentResolver.query( // Делаем запрос в системный ContentProvider
            Telephony.Sms.CONTENT_URI,
            null,
            null,
            null,
            null
        )

        val result = AddressAndMessageList(mutableListOf())

        if (cursor?.moveToFirst() == true) {
            do {
                val address = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.ADDRESS))
                val body = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.BODY))
                val time = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.DATE))
                val type = cursor.getInt(cursor.getColumnIndexOrThrow(Telephony.Sms.TYPE))
                val isSent = type == Telephony.Sms.MESSAGE_TYPE_SENT
                Log.d("SmsListViewModel", "$time | ${time.toLong()} | ${timestampToString(time.toLong())} | $address")
                result.list.add(address to MessageListItem(body, timestampToString(time.toLong()), isSent))
            } while (cursor.moveToNext())
        }

        cursor?.close() // cursor в результате всегда должен закрываться

        _chatMessageItems.postValue(result.toSmsListItemsList())
    }

    private fun timestampToString(timestamp: Long) =
        formatter.format(convertMillisToDateTime(timestamp))


    private fun convertMillisToDateTime(millis: Long): LocalDateTime {
        val instant = Instant.ofEpochMilli(millis)
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    }

    companion object {
        private const val TIME_FORMAT = "HH:mm"
        private val formatter =
            DateTimeFormatter.ofPattern(TIME_FORMAT)
    }
}

@JvmInline
value class AddressAndMessageList(
    val list: MutableList<Pair<String, MessageListItem>>
) {
    fun toSmsListItemsList(): List<SmsListItem> =
        list
            .groupBy { it.first }
            .map {
                val messages = it.value.map { it.second }
                SmsListItem(it.key, messages)
            }
}