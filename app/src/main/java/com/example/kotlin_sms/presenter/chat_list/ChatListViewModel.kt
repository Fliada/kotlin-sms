package com.example.kotlin_sms.presenter.chat_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_sms.data.item.MessageListItem
import com.example.kotlin_sms.data.item.SmsListItem

class ChatListViewModel : ViewModel() {

    private val _messageListItems = MutableLiveData<List<MessageListItem>>()
    val messageEntries: LiveData<List<MessageListItem>>
        get() = _messageListItems


    fun loadSmsMessages(smsListItem : SmsListItem) {

        _messageListItems.postValue(smsListItem.messages)

    }
}