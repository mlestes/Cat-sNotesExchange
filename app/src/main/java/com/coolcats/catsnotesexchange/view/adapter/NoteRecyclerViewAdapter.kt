package com.coolcats.catsnotesexchange.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coolcats.catsnotesexchange.R
import com.coolcats.catsnotesexchange.mod.Notes
import kotlinx.android.synthetic.main.feed_list_item.view.*

class NoteRecyclerViewAdapter(private val noteDelegate: NoteDelegate) :
    RecyclerView.Adapter<NoteRecyclerViewAdapter.NoteViewHolder>() {

    var noteList: List<Notes> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view)

    interface NoteDelegate {
        fun showNote(note: Notes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.feed_list_item,
                    parent,
                    false
                )
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        val note = noteList[position]
        holder.itemView.apply {
            this.note_title_txt.text = note.title
            this.subject_txt.text = note.subject
            this.author_txt.text = "Submitted by: ${note.author}"
        }

        holder.itemView.setOnClickListener { noteDelegate.showNote(note) }

    }

    override fun getItemCount(): Int = noteList.size

}