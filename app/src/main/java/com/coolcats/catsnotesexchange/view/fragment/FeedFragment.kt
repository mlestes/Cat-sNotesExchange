package com.coolcats.catsnotesexchange.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.coolcats.catsnotesexchange.R
import com.coolcats.catsnotesexchange.mod.Notes
import com.coolcats.catsnotesexchange.util.Util.Companion.getNotesDB
import com.coolcats.catsnotesexchange.util.Util.Companion.showError
import com.coolcats.catsnotesexchange.util.UtilStatus
import com.coolcats.catsnotesexchange.view.adapter.NoteRecyclerViewAdapter
import com.coolcats.catsnotesexchange.viewmodel.AppViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.feed_fragment_layout.*

class FeedFragment : Fragment(), NoteRecyclerViewAdapter.NoteDelegate {

    private val notesReference = getNotesDB()
    private val adapter = NoteRecyclerViewAdapter(this)
    private val viewModel: AppViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.feed_fragment_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        feed_recycler.adapter = adapter

        viewModel.statusData.value = UtilStatus.LOADING
        notesReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<Notes>()
                snapshot.children.forEach { snap ->
                    snap.getValue(Notes::class.java)?.let { item ->
                        list.add(item)
                    }
                }
                adapter.noteList = list
                viewModel.statusData.value = UtilStatus.SUCCESS
            }

            override fun onCancelled(error: DatabaseError) {
                showError(view, error.message)
                viewModel.statusData.value = UtilStatus.ERROR
            }
        })
    }

    override fun showNote(note: Notes) {
        val fragment = DisplayNoteFragment(note)
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            .replace(R.id.show_note_frame, fragment)
            .addToBackStack(fragment.tag)
            .commit()
    }

}