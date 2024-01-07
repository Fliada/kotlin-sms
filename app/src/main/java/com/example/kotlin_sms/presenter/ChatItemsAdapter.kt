package com.example.kotlin_sms.presenter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_sms.R
import com.example.kotlin_sms.data.item.MessageListItem
import com.example.kotlin_sms.data.item.SmsListItem
import com.example.kotlin_sms.databinding.MessageListItemBinding
import com.example.kotlin_sms.databinding.SmsListItemBinding

class ChatItemsAdapter(
) : RecyclerView.Adapter<ChatItemsAdapter.ChatEntryViewHolder>() {

    private val list = mutableListOf<MessageListItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatEntryViewHolder {
        val context = parent.context
        val layoutInflater = LayoutInflater.from(context)
        val binding = MessageListItemBinding.inflate(layoutInflater, parent, false)
        return ChatEntryViewHolder(binding)
    }

    override fun getItemCount(): Int =
        list.size

    override fun onBindViewHolder(holder: ChatEntryViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun submitList(list: List<MessageListItem>) = with(this.list) {
        clear()
        addAll(list)
        notifyDataSetChanged()
    }

    inner class ChatEntryViewHolder(
        private val binding: MessageListItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entry: MessageListItem) = with(binding) {
            val context = binding.root.context

            messageTextView.text = entry.message

            timeTextView.text = entry.time

            val params = cardCheckedLayerId.layoutParams as ConstraintLayout.LayoutParams
            if (!entry.isSent) {
                cardCheckedLayerId.setCardBackgroundColor(context.getColor(R.color.gray))
                params.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                params.endToEnd = View.NO_ID
            }
            else {
                cardCheckedLayerId.setCardBackgroundColor(context.getColor(R.color.purple))
                params.startToStart = View.NO_ID
                params.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
            }
        }
    }
}