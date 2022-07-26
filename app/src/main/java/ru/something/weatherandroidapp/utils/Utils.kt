package ru.something.weatherandroidapp.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

private fun View.showSnackBar(
    text: String,
    actionText: String,
    action: (View) -> Unit,
    length: Int = Snackbar.LENGTH_INDEFINITE
) {
    Snackbar.make(this, text, length).setAction(actionText, action).show()
}

fun View.createAndShow(text: String, actionText: String, action: (View) -> Unit,
                       length: Int = Snackbar.LENGTH_INDEFINITE) {
    Snackbar.make(this, text, length).setAction(actionText, action).show()
}

fun View.show() : View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}
fun View.hide() : View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}

