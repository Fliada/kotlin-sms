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

class SmsItemsAdapter(
    private val onItemClick: (SmsListItem) -> Unit,
) : RecyclerView.Adapter<SmsItemsAdapter.SmsEntryViewHolder>() {

    private val list = mutableListOf<SmsListItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmsEntryViewHolder {
        val context = parent.context
        val layoutInflater = LayoutInflater.from(context)
        val binding = SmsListItemBinding.inflate(layoutInflater, parent, false)
        return SmsEntryViewHolder(binding, onItemClick)
    }

    override fun getItemCount(): Int =
        list.size

    override fun onBindViewHolder(holder: SmsEntryViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun submitList(list: List<SmsListItem>) = with(this.list) {
        clear()
        addAll(list)
        notifyDataSetChanged()
    }

    inner class SmsEntryViewHolder(
        private val binding: SmsListItemBinding,
        private val onItemClick: (SmsListItem) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entry: SmsListItem) = with(binding) {
            val context = binding.root.context

            titleTextView.text = entry.address

            val message = entry.messages.first()

            messageTextView.text = message.message
            timeTextView.text = message.time

            root.setOnClickListener {
                onItemClick(entry)
            }
        }
    }
}