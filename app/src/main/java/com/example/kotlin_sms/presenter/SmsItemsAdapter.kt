package com.example.kotlin_sms.presenter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_sms.data.item.SmsListItem
import com.example.kotlin_sms.databinding.SmsListItemBinding

class SmsItemsAdapter : RecyclerView.Adapter<SmsItemsAdapter.SmsEntryViewHolder>() {

    private val list = mutableListOf<SmsListItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmsEntryViewHolder {
        val context = parent.context
        val layoutInflater = LayoutInflater.from(context)
        val binding = SmsListItemBinding.inflate(layoutInflater, parent, false)
        return SmsEntryViewHolder(binding)
    }

    override fun getItemCount(): Int =
        list.size

    override fun onBindViewHolder(holder: SmsEntryViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun submitList(list: List<SmsListItem>) {
        this.list.addAll(list)
    }

    inner class SmsEntryViewHolder(
        private val binding: SmsListItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entry: SmsListItem) = with(binding) {
            val context = binding.root.context

            titleTextView.text = entry.address
            messageTextView.text = entry.messages.first().message
            timeTextView.text = entry.messages.first().time
        }
    }
}