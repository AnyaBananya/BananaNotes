package geekbrains.ru.banananotes.model.provider

import androidx.lifecycle.LiveData
import geekbrains.ru.banananotes.model.Note
import geekbrains.ru.banananotes.model.NoteResult
import geekbrains.ru.banananotes.model.User

interface RemoteDataProvider {
    fun subscribeToAllNotes(): LiveData<NoteResult>
    fun getNoteById(id: String): LiveData<NoteResult>
    fun saveNote(note: Note): LiveData<NoteResult>
    fun getCurrentUser(): LiveData<User?>
}