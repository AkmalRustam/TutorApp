package com.akmaldev.tutorapp.presentation.ui.tutornotification.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akmaldev.tutorapp.R
import com.akmaldev.tutorapp.data.model.response.main.tutornotification.TutorNotificationsData
import com.akmaldev.tutorapp.databinding.RecyclerViewNotificationItemBinding

class TutorNotificationAdapter :
    RecyclerView.Adapter<TutorNotificationAdapter.TutorNotificationViewHolder>() {

    private var notifications: List<TutorNotificationsData> = emptyList()

    inner class TutorNotificationViewHolder(private val binding: RecyclerViewNotificationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(data: TutorNotificationsData) {
            with(binding) {
                ivStatus.backgroundTintList = ivStatus.context.getColorStateList(R.color.blue)
                tvStatus.text = data.message
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutorNotificationViewHolder {
        return TutorNotificationViewHolder(
            RecyclerViewNotificationItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun getItemCount(): Int = notifications.size

    override fun onBindViewHolder(holder: TutorNotificationViewHolder, position: Int) {
        holder.onBind(notifications[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(notifications: List<TutorNotificationsData>) {
        this.notifications = notifications
        notifyDataSetChanged()
    }
}