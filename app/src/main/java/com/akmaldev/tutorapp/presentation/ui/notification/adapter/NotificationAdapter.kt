package com.akmaldev.tutorapp.presentation.ui.notification.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akmaldev.tutorapp.R
import com.akmaldev.tutorapp.data.model.response.main.notification.NotificationData
import com.akmaldev.tutorapp.data.model.response.main.notification.NotificationResponseData
import com.akmaldev.tutorapp.databinding.RecyclerViewNotificationItemBinding

class NotificationAdapter : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    private var notifications: List<NotificationData> = emptyList()

    private var onItemClickListener: ((NotificationData) -> Unit)? = null

    inner class NotificationViewHolder(private val binding: RecyclerViewNotificationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(data: NotificationData) {
            with(binding) {
                when (data.status) {
                    RED -> {
                        ivStatus.setImageResource(R.drawable.ic_red_status)
                        tvStatus.text = "Afsuski, siz \"Qizil\" toifadasiz"
                    }

                    GREEN -> {
                        ivStatus.setImageResource(R.drawable.ic_green_status)
                        tvStatus.text = "Tabriklaymiz, siz \"Yashil\" toifadasiz"
                    }

                    YELLOW -> {
                        ivStatus.setImageResource(R.drawable.ic_yellow_status)
                        tvStatus.text = "Tabriklaymiz, siz \"Sariq\" toifadasiz"
                    }
                }
                root.setOnClickListener {
                    onItemClickListener?.invoke(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        return NotificationViewHolder(
            RecyclerViewNotificationItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun getItemCount(): Int = notifications.size

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.onBind(notifications[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(notifications: List<NotificationData>) {
        this.notifications = notifications
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(callback: (NotificationData) -> Unit) {
        onItemClickListener = callback
    }

    companion object {
        private const val RED = "red"
        private const val GREEN = "green"
        private const val YELLOW = "yellow"
    }
}