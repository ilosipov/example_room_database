package com.ilosipov.exampleroomdatabase.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.ilosipov.exampleroomdatabase.R
import com.ilosipov.exampleroomdatabase.model.User
import com.ilosipov.exampleroomdatabase.viewmodel.UserViewModel
import com.ilosipov.exampleroomdatabase.utils.ShowSoftwareKeyboard
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

/**
 * Класс AddFragment
 * @author Ilya Osipov (mailto:il.osipov.gm@gmail.com)
 * @since 05.07.2020
 * @version $Id$
 */

class AddFragment : Fragment() {

    private lateinit var userViewModel : UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) : View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.btn_save_user.apply {
            setOnClickListener { insertDataToDatabase() }
        }
        return view
    }

    private fun insertDataToDatabase() {
        val firstName = first_name_add.text.toString().trim()
        val lastName = last_name_add.text.toString().trim()
        val age = age_add.text!!

        if (inputCheck(firstName, lastName, age)) {
            // Create User Object
            val user = User(
                0,
                firstName,
                lastName,
                Integer.parseInt(age.toString())
            )
            userViewModel.addUser(user)
            Snackbar.make(this.requireView(), getString(R.string.text_snack_bar_success),
                Snackbar.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
            ShowSoftwareKeyboard(requireActivity()).show(false)
        } else {
            // Not Create User Object
            Snackbar.make(this.requireView(), getString(R.string.text_snack_bar_error),
                Snackbar.LENGTH_SHORT).show()
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