package geekbrains.ru.banananotes.model.repository

import geekbrains.ru.banananotes.model.Note
import geekbrains.ru.banananotes.model.provider.RemoteDataProvider

class Repository(private val remoteDataProvider: RemoteDataProvider) {

    fun getNotes() = remoteDataProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = remoteDataProvider.saveNote(note)
    fun getNoteById(id: String) = remoteDataProvider.getNoteById(id)
    fun getCurrentUser() = remoteDataProvider.getCurrentUser()
    fun deleteNote(noteId: String) = remoteDataProvider.deleteNote(noteId)

}