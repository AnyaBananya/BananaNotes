package geekbrains.ru.banananotes.viewmodel

import androidx.lifecycle.Observer
import geekbrains.ru.banananotes.model.Note
import geekbrains.ru.banananotes.model.NoteResult
import geekbrains.ru.banananotes.model.repository.Repository
import geekbrains.ru.banananotes.ui.viewstate.NoteViewState
import kotlinx.coroutines.launch
import java.util.*


class NoteViewModel(val repository: Repository) :
    BaseViewModel<NoteViewState.Data>() {

    private val currentNote: Note?
        get() = getViewState().poll()?.note

    fun saveChanges(note: Note) {
        setData(NoteViewState.Data(note = note))
    }

    fun loadNote(noteId: String) {
        launch {
            try {
                setData(NoteViewState.Data(note = repository.getNoteById(noteId)))
            } catch (e: Throwable) {
                setError(e)
            }
        }
    }

    fun deleteNote() {
        launch {
            try {
                currentNote?.let { repository.deleteNote(it.id) }
                setData(NoteViewState.Data(isDeleted = true))
            } catch (e: Throwable) {
                setError(e)
            }
        }
    }

    override fun onCleared() {
        launch {
            currentNote?.let { repository.saveNote(it) }
            super.onCleared()
        }
    }
}
