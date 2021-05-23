package com.coolcats.catsnotesexchange.view.fragment

import android.os.Bundle
import android.text.Editable
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.coolcats.catsnotesexchange.R
import com.coolcats.catsnotesexchange.mod.Notes
import com.coolcats.catsnotesexchange.util.Util.Companion.getNotesDB
import com.coolcats.catsnotesexchange.viewmodel.AppViewModel
import kotlinx.android.synthetic.main.display_note_fragment_layout.*

class DisplayNoteFragment(private val note: Notes) : Fragment() {

    private val viewModel : AppViewModel by activityViewModels()
    private val notesReference = getNotesDB()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.display_note_fragment_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = viewModel.userData.value

        show_note_title_txt.text = note.title
        snote_subject_txt.text = note.subject
        note_author_txt.text = note.author
        note_body_txt.text = note.body
        note_body_txt.movementMethod = ScrollingMovementMethod()

        edit_body_btn.setOnClickListener {
            cnx_body_btn.visibility = View.VISIBLE
            note_body_txt.visibility = View.INVISIBLE
            note_edit_txt.visibility = View.VISIBLE
            note_edit_txt.text = note.body.toEditable()
            okay_btn.visibility = View.VISIBLE
            edit_body_btn.visibility = View.INVISIBLE
        }

        okay_btn.setOnClickListener {
            note.body = note_edit_txt.text.toString()
            note.author = "${user?.userName} as Editor"
            note_author_txt.text = note.author
            notesReference.child(note.key).setValue(note)
            edit_body_btn.visibility = View.VISIBLE
            okay_btn.visibility = View.INVISIBLE
            cnx_body_btn.visibility = View.INVISIBLE
            note_body_txt.text = note.body
            note_body_txt.visibility = View.VISIBLE
            note_edit_txt.visibility = View.INVISIBLE
        }

        cnx_body_btn.setOnClickListener {
            edit_body_btn.visibility = View.VISIBLE
            okay_btn.visibility = View.INVISIBLE
            cnx_body_btn.visibility = View.INVISIBLE
            note_body_txt.visibility = View.VISIBLE
            note_edit_txt.visibility = View.INVISIBLE
        }


    }

}

private fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
