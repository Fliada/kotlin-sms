package com.example.kotlin_sms.presenter.chat_list

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlin_sms.R
import com.example.kotlin_sms.data.item.SmsListItem
import com.example.kotlin_sms.databinding.FragmentChatListBinding
import com.example.kotlin_sms.presenter.ChatItemsAdapter
import com.example.kotlin_sms.requirePermission

class ChatListFragment : Fragment(R.layout.fragment_chat_list) {

    private val binding: FragmentChatListBinding by viewBinding()
    private val args: ChatListFragmentArgs by navArgs()
    private val viewModel: ChatListViewModel by viewModels()

    private val adapter = ChatItemsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val smsListItem = args.smsListItem

        viewModel.messageEntries.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        initializeRecycler()
        requireSmsPermission(smsListItem)
    }

    private fun requireSmsPermission(smsListItem : SmsListItem) {
        requirePermission(
            permission = Manifest.permission.READ_SMS,
            successDelegate = {
                viewModel.loadSmsMessages(smsListItem)
            },
            failureDelegate = {

            }
        )
    }

    private fun initializeRecycler() = with(binding.messageListRecycler) {
        layoutManager = LinearLayoutManager(requireContext())
        (layoutManager as LinearLayoutManager).reverseLayout = true
        (layoutManager as LinearLayoutManager).stackFromEnd = false

        adapter = this@ChatListFragment.adapter
        addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
    }
}