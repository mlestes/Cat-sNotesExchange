package com.coolcats.catsnotesexchange.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.coolcats.catsnotesexchange.R
import com.coolcats.catsnotesexchange.mod.Notes
import com.coolcats.catsnotesexchange.mod.User
import com.coolcats.catsnotesexchange.util.Konstants.Companion.SUBJECT_ARRAY
import com.coolcats.catsnotesexchange.util.Util.Companion.getLoggedInUser
import com.coolcats.catsnotesexchange.util.Util.Companion.getNotesDB
import com.coolcats.catsnotesexchange.util.Util.Companion.getUserDB
import com.coolcats.catsnotesexchange.util.Util.Companion.myLog
import com.coolcats.catsnotesexchange.util.Util.Companion.showError
import com.coolcats.catsnotesexchange.viewmodel.AppViewModel
import kotlinx.android.synthetic.main.new_note_fragment_layout.*

class CreateNoteFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var name: String
    private lateinit var subject: String
    private val noteReference = getNotesDB()
    private val viewModel: AppViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.new_note_fragment_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.createStatusData.value = false

        val adapter =
            ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                SUBJECT_ARRAY
            )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        subject_spinner.adapter = adapter
        subject_spinner.onItemSelectedListener = this

        val user = viewModel.userData.value
        name = user?.userName ?: "No User Data"

        note_submit_btn.setOnClickListener {
            val title = note_title_input.text.toString()
            val body = note_body_input.text.toString()
            val key = noteReference.push().key ?: ""
            if (this::subject.isInitialized &&
                body.isNotEmpty() &&
                title.isNotEmpty()
            ) {
                val note = Notes(key, subject, name, title, body)
                noteReference.child(key).setValue(note)
                val count = requireActivity().supportFragmentManager.backStackEntryCount
                for(i in 0..count) requireActivity().supportFragmentManager.popBackStack()
            } else
                showError(view, "All fields must not be empty")

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.createStatusData.value = true
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (position > 0) {
            subject = SUBJECT_ARRAY[position]
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        /* do nothinng */
    }

}