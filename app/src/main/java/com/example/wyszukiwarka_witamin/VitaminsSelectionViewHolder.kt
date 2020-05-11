package com.example.wyszukiwarka_witamin

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VitaminsSelectionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val vitaminName = itemView.findViewById(R.id.vitamin_string) as TextView
}