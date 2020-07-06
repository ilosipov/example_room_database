package com.ilosipov.exampleroomdatabase.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilosipov.exampleroomdatabase.R
import com.ilosipov.exampleroomdatabase.data.User
import kotlinx.android.synthetic.main.custom_row.view.*

/**
 * Класс ListAdapter
 * @author Ilya Osipov (mailto:il.osipov.gm@gmail.com)
 * @since 06.07.2020
 * @version $Id$
 */

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var userList = emptyList<User>()

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ListViewHolder =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = userList[position]

        holder.itemView.text_id.text = item.id.toString()
        holder.itemView.text_first_name.text = item.firstName
        holder.itemView.text_last_name.text = item.lastName
        holder.itemView.text_age.text = item.age.toString()
    }

    override fun getItemCount() : Int = userList.size

    fun setData(users: List<User>) {
        this.userList = users
        notifyDataSetChanged()
    }
}