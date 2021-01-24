package geekbrains.ru.banananotes.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

object Repository {

    private val notesLiveData = MutableLiveData<List<Note>>()

    private val notes: MutableList<Note> = mutableListOf(
        Note(
            id = UUID.randomUUID().toString(),
            title = "My first note",
            note = "Kotlin is a very short, but expressive language",
            color = Color.PINK
        ),
        Note(
            id = UUID.randomUUID().toString(),
            title = "My second note",
            note = "Kotlin is a very short, but expressive language",
            color = Color.BLUE
        ),
        Note(
            id = UUID.randomUUID().toString(),
            title = "My third note",
            note = "Kotlin is a very short, but expressive language",
            color = Color.GREEN
        )
    )

    init {
        notesLiveData.value = notes
    }

    fun getNotes(): LiveData<List<Note>> = notesLiveData

    fun saveNote(note: Note) {
        addOrReplace(note)
        notesLiveData.value = notes
    }

    private fun addOrReplace(note: Note) {
        for (i in 0 until notes.size) {
            if (notes[i] == note) {
                notes[i] = note
                return
            }
        }

        notes.add(note)
    }
}