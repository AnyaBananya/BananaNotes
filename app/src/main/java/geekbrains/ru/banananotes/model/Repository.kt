package geekbrains.ru.banananotes.model

object Repository {

    private val notes: MutableList<Note> = mutableListOf(
        Note(
            "My first note",
            "Kotlin is a very short, but expressive language",
            0xff9575cd.toInt()
        ),
        Note(
            "My second note",
            "Kotlin is a very short, but expressive language",
            0xff64b5f6.toInt()
        ),
        Note(
            "My third note",
            "Kotlin is a very short, but expressive language",
            0xff4db6ac.toInt()
        )
    )

    fun getNotes(): List<Note> {
        return notes
    }

    fun addNote(note: Note) {
        notes.add(note)
    }
}