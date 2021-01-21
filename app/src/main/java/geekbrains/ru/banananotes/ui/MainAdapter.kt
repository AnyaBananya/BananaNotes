package geekbrains.ru.banananotes.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import geekbrains.ru.banananotes.R
import geekbrains.ru.banananotes.databinding.ItemNoteBinding
import geekbrains.ru.banananotes.model.Note

class MainAdapter : RecyclerView.Adapter<MainAdapter.NoteViewHolder>() {

    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount(): Int = notes.size

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ui: ItemNoteBinding = ItemNoteBinding.bind(itemView)

        fun bind(note: Note) {
            ui.title.text = note.title
            ui.body.text = note.note
            itemView.setBackgroundColor(note.color)
        }
    }
}