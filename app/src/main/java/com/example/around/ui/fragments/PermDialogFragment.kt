package com.example.around.ui.fragments

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.around.R

class PermDialogFragment : DialogFragment(), DialogInterface.OnClickListener {

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    return activity?.let {
      val builder = AlertDialog.Builder(it)
      builder.setMessage(R.string.perm_location_rationale)
        .setPositiveButton(R.string.perm_location_acceptance, this)
        .setNegativeButton(R.string.perm_location_cancel, this)
      // Create the AlertDialog object and return it
      builder.create()
    } ?: throw IllegalStateException("Activity cannot be null")
  }

  override fun onClick(dialog: DialogInterface?, which: Int) {
    activity?.takeIf { it is DialogInterface.OnClickListener }?.let {
      (it as DialogInterface.OnClickListener).onClick(dialog, which)
    } ?: dismissAllowingStateLoss()
  }

}