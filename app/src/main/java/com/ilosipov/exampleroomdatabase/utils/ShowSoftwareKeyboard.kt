package com.ilosipov.exampleroomdatabase.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

/**
 * Класс ShowSoftwareKeyboard
 * @author Ilya Osipov (mailto:il.osipov.gm@gmail.com)
 * @since 06.07.2020
 * @version $Id$
 */

class ShowSoftwareKeyboard(private val activity: Activity) : ShowKeyboard {

    private val inputMethod = activity
        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?

    override fun show(showKeyboard: Boolean) {
        inputMethod?.hideSoftInputFromWindow(activity.currentFocus?.windowToken,
            if (showKeyboard) InputMethodManager.SHOW_FORCED else InputMethodManager.HIDE_NOT_ALWAYS)
    }
}