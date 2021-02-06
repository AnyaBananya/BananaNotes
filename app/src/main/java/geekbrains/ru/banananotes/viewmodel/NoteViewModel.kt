package geekbrains.ru.banananotes.viewmodel

import androidx.lifecycle.Observer
import geekbrains.ru.banananotes.model.Note
import geekbrains.ru.banananotes.model.NoteResult
import geekbrains.ru.banananotes.model.repository.Repository
import geekbrains.ru.banananotes.ui.viewstate.NoteViewState
import java.util.*


class NoteViewModel(val repository: Repository) :
    BaseViewModel<NoteViewState.Data, NoteViewState>() {

    private val currentNote: Note?
        get() = viewStateLiveData.value?.data?.note

    fun saveChanges(note: Note) {
        viewStateLiveData.value = NoteViewState(NoteViewState.Data(note = note))
    }

    override fun onCleared() {
        currentNote?.let {
            repository.saveNote(it)
        }
    }

    fun addNote(title: String, note: String): Note {
        val newNote = Note(id = UUID.randomUUID().toString(), title = title, note = note)
        Repository.saveNote(newNote)
        return newNote
    }

    fun loadNote(noteId: String) {
        repository.getNoteById(noteId).observeForever(Observer { t ->
            t?.let { noteResult ->
                viewStateLiveData.value = when (noteResult) {
                    is NoteResult.Success<*> -> NoteViewState(NoteViewState.Data(note = noteResult.data as? Note))
                    is NoteResult.Error -> NoteViewState(error = noteResult.error)
                }
            }
        })
    }

    fun deleteNote() {
        currentNote?.let {
            repository.deleteNote(it.id).observeForever { result ->
                result?.let { noteResult ->
                    viewStateLiveData.value = when (noteResult) {
                        is NoteResult.Success<*> -> NoteViewState(NoteViewState.Data(isDeleted = true))
                        is NoteResult.Error -> NoteViewState(error = noteResult.error)
                    }
                }
            }
        }
    }
}
