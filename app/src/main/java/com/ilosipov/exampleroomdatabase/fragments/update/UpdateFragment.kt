package com.ilosipov.exampleroomdatabase.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
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

        setHasOptionsMenu(true)

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_delete, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) : Boolean {
        if (item.itemId == R.id.btn_delete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton(getString(R.string.positive_btn_dialog)) { _, _ ->
            userViewModel.deleteUser(args.currentUser)
            Snackbar.make(this.requireView(), String.format(
                getString(R.string.text_snack_bar_delete), args.currentUser.firstName),
                Snackbar.LENGTH_SHORT).show()
            ShowSoftwareKeyboard(requireActivity()).show(false)
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton(getString(R.string.negative_btn_dialog)) { _, _ -> }
        builder.setTitle(String.format(
            getString(R.string.title_delete_user),
            args.currentUser.firstName,
            args.currentUser.lastName))
        builder.setMessage(String.format(
            getString(R.string.message_delete_user),
            args.currentUser.firstName))
        builder.create().show()
    }

    override fun onDestroy() {
        super.onDestroy()
        ShowSoftwareKeyboard(requireActivity()).show(false)
    }
}