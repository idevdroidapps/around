package com.example.around.ui.utils

import android.view.inputmethod.EditorInfo
import android.widget.TextView

fun TextView.onClickKeyboardDoneButton(funExecute: () -> Unit) {
  this.setOnEditorActionListener { textView, actionId, keyEvent ->
    when (actionId) {
      EditorInfo.IME_ACTION_SEARCH,
      EditorInfo.IME_ACTION_NEXT,
      EditorInfo.IME_ACTION_DONE,
      EditorInfo.IME_ACTION_GO -> {
        funExecute.invoke()
        true
      }
      else -> false
    }
  }
}