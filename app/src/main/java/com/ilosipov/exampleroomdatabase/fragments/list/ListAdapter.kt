package com.ilosipov.exampleroomdatabase.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ilosipov.exampleroomdatabase.R
import com.ilosipov.exampleroomdatabase.model.User
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
        holder.itemView.text_first_name.text = String.format("%s %s", item.firstName, item.lastName)
        holder.itemView.text_age.text = item.age.toString()

        holder.itemView.row_layout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(item)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount() : Int = userList.size

    fun setData(users: List<User>) {
        this.userList = users
        notifyDataSetChanged()
    }
}