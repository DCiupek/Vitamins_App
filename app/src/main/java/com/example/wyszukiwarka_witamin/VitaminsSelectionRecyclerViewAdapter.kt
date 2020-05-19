package com.example.wyszukiwarka_witamin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class VitaminsSelectionRecyclerViewAdapter(private val lists: List<Vitamins>,
                                           val clickListener: MainActivity
): RecyclerView.Adapter<VitaminsSelectionViewHolder> (){
    val vitaminsNames = lists
    val vitaminNamesfull= lists

    override fun getItemCount(): Int {
        return vitaminsNames.size
    }

    override fun onBindViewHolder(holder: VitaminsSelectionViewHolder, position: Int) {
        holder.vitaminName.text = vitaminsNames[position].id
        holder.itemView.setOnClickListener {
            clickListener.vitaminItemClicked(vitaminsNames[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VitaminsSelectionViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.vitamin_selection_view_holder,
                parent,
                false)

        return VitaminsSelectionViewHolder(view)
    }



    interface VitaminSelectionRecyclerViewClickListener {
        fun vitaminItemClicked(vitamin: Vitamins)
    }
}