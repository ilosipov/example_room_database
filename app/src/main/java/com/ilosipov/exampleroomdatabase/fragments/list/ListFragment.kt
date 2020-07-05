package com.ilosipov.exampleroomdatabase.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ilosipov.exampleroomdatabase.R
import kotlinx.android.synthetic.main.fragment_list.view.*

/**
 * Класс ListFragment
 * @author Ilya Osipov (mailto:il.osipov.gm@gmail.com)
 * @since 05.07.2020
 * @version $Id$
 */

class ListFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) : View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        view.floatingActionButton.apply {
            setOnClickListener { findNavController().navigate(R.id.action_listFragment_to_addFragment) }
        }

        return view
    }

}