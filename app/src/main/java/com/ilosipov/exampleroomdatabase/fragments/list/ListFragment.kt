package com.ilosipov.exampleroomdatabase.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ilosipov.exampleroomdatabase.R
import com.ilosipov.exampleroomdatabase.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

/**
 * Класс ListFragment
 * @author Ilya Osipov (mailto:il.osipov.gm@gmail.com)
 * @since 05.07.2020
 * @version $Id$
 */

class ListFragment : Fragment() {

    private lateinit var userViewModel : UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) : View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // RecyclerView
        val adapter = ListAdapter()
        val recyclerView = view.recycler_view
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // UserViewModel
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.readAllData.observe(this.viewLifecycleOwner, Observer {
            adapter.setData(it)
        })

        view.floatingActionButton.apply {
            setOnClickListener { findNavController().navigate(R.id.action_listFragment_to_addFragment) }
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_delete, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.btn_delete) {
            deleteAllUsers()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton(getString(R.string.positive_btn_dialog)) { _, _ ->
            userViewModel.deleteAllUsers()
            Snackbar.make(this.requireView(), String.format(
                getString(R.string.text_snack_bar_delete_all)),
                Snackbar.LENGTH_SHORT).show()
        }
        builder.setNegativeButton(getString(R.string.negative_btn_dialog)) { _, _ -> }
        builder.setTitle(getString(R.string.title_delete_all))
        builder.setMessage(getString(R.string.message_delete_all))
        builder.create().show()
    }
}