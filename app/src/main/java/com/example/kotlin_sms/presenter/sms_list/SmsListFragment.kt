package com.example.kotlin_sms.presenter.sms_list

import android.Manifest
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlin_sms.R
import com.example.kotlin_sms.data.item.MessageListItem
import com.example.kotlin_sms.data.item.SmsListItem
import com.example.kotlin_sms.databinding.FragmentSmsListBinding
import com.example.kotlin_sms.presenter.SmsItemsAdapter
import com.example.kotlin_sms.requirePermission

class SmsListFragment : Fragment(R.layout.fragment_sms_list) {

    private val binding: FragmentSmsListBinding by viewBinding()
    private val viewModel: SmsListViewModel by viewModels()

    private val adapter = SmsItemsAdapter(
        ::onChatItemClick,
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecycler()
        requireSmsPermission()
    }

    private fun requireSmsPermission() {
        requirePermission(
            permission = Manifest.permission.READ_SMS,
            successDelegate = {
                fillMockData()
            },
            failureDelegate = {

            }
        )
    }

    private fun fillMockData() {
        adapter.submitList(
            listOf(
                SmsListItem("Кто ты", listOf(MessageListItem("Hello world", "12:34"))),
                SmsListItem("ААААААААААА", listOf(MessageListItem("Bye namespace", "19:12"))),
                SmsListItem("OMG", listOf(MessageListItem("HIIIIIII!!!!!!", "11:34"))),
            )
        )
    }

    private fun onChatItemClick(entry: SmsListItem) {
        Toast.makeText(requireContext(), entry.address, Toast.LENGTH_SHORT).show()
    }

    private fun initializeRecycler() = with(binding.smsListRecycler) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = this@SmsListFragment.adapter
        addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
    }
}