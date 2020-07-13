package com.ilosipov.exampleroomdatabase.fragments.update

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.ilosipov.exampleroomdatabase.R
import com.ilosipov.exampleroomdatabase.model.User
import com.ilosipov.exampleroomdatabase.utils.ShowSoftwareKeyboard
import com.ilosipov.exampleroomdatabase.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

/**
 * Класс UpdateFragment
 * @author Ilya Osipov (mailto:il.osipov.gm@gmail.com)
 * @since 13.07.2020
 * @version $Id$
 */

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var userViewModel : UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) : View? {
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.update_first_name.setText(args.currentUser.firstName)
        view.update_last_name.setText(args.currentUser.lastName)
        view.update_age.setText(args.currentUser.age.toString())

        view.btn_update_user.setOnClickListener {
            updateItem()
        }

        return view
    }

    private fun updateItem() {
        val firstName = update_first_name.text.toString()
        val lastName = update_last_name.text.toString()
        val age = Integer.parseInt(update_age.text.toString())

        if (inputCheck(firstName, lastName, update_age.text!!)) {
            val updateUser = User(args.currentUser.id, firstName, lastName, age)
            userViewModel.updateUser(updateUser)
            Snackbar.make(this.requireView(), getString(R.string.text_snack_bar_update),
                Snackbar.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            ShowSoftwareKeyboard(requireActivity()).show(false)
        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable) : Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    override fun onDestroy() {
        super.onDestroy()
        ShowSoftwareKeyboard(requireActivity()).show(false)
    }
}