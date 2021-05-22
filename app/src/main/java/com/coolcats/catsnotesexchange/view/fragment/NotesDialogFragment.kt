package com.coolcats.catsnotesexchange.view.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class NotesDialogFragment : DialogFragment() {

    companion object {
        const val tag = "NotesDialogFragment"
        private const val TITLE_KEY = "TITLE_KEY"
        private const val INPUT_KEY = "INPUT_KEY"
        fun newInstance(title: String, input: String) : NotesDialogFragment{
            val args = Bundle()
            args.putString(TITLE_KEY, title)
            args.putString(INPUT_KEY, input)
            val fragment = NotesDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val title = arguments?.getString(TITLE_KEY)
        val input = arguments?.getString(INPUT_KEY)
        val builder = AlertDialog.Builder(activity)
            .setTitle(title)
            .setMessage(input)
            .setPositiveButton("Okay") { d, _ -> d.dismiss() }

        return builder.create()


    }

}