package com.example.kotlin_sms.presenter.sms_list

import android.os.Bundle
import android.view.View
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

class SmsListFragment : Fragment(R.layout.fragment_sms_list) {

    private val binding: FragmentSmsListBinding by viewBinding()
    private val viewModel: SmsListViewModel by viewModels()
    private val adapter = SmsItemsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecycler()
        adapter.submitList(
            listOf(
                SmsListItem("Кто ты", listOf(MessageListItem("Hello world", "12:34"))),
                SmsListItem("Кто ты", listOf(MessageListItem("Bye namespace", "19:12"))),
                SmsListItem("OMG", listOf(MessageListItem("HIIIIIII!!!!!!", "11:34"))),
            )
        )
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