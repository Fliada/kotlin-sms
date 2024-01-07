package com.example.kotlin_sms.presenter.chat_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.kotlin_sms.R
import com.example.kotlin_sms.data.item.SmsListItem
import androidx.navigation.fragment.navArgs
import com.example.kotlin_sms.presenter.sms_list.SmsListViewModel

class ChatListFragment : Fragment(R.layout.fragment_chat_list) {

    private val args: ChatListFragmentArgs by navArgs()
    private val viewModel: ChatListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val smsListItem = args.smsListItem
    }
}