package geekbrains.ru.banananotes.viewmodel

import androidx.lifecycle.ViewModel
import geekbrains.ru.banananotes.model.Note
import geekbrains.ru.banananotes.model.Repository
import java.util.*

class NoteViewModel(private val repository: Repository = Repository) : ViewModel() {

    private var pendingNote: Note? = null

    fun saveChanges(note: Note) {
        pendingNote = note
    }

    override fun onCleared() {
        if (pendingNote != null) {
            repository.saveNote(pendingNote!!)
        }
    }

    fun addNote(title: String, note: String): Note {
        val newNote = Note(id = UUID.randomUUID().toString(), title = title, note = note)
        Repository.saveNote(newNote)
        return newNote
    }
}