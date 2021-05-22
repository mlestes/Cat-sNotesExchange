package com.coolcats.catsnotesexchange.view.fragment

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.coolcats.catsnotesexchange.R
import com.coolcats.catsnotesexchange.mod.Notes
import com.coolcats.catsnotesexchange.viewmodel.AppViewModel
import kotlinx.android.synthetic.main.display_note_fragment_layout.*

class DisplayNoteFragment(private val note: Notes) : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.display_note_fragment_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        show_note_title_txt.text = note.title
        snote_subject_txt.text = note.subject
        note_author_txt.text = "Submitted by: ${note.author}"
        note_body_txt.text = note.body
        note_body_txt.movementMethod = ScrollingMovementMethod()

    }

}