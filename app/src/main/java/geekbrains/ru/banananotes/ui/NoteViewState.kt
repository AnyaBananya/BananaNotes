package geekbrains.ru.banananotes.ui

import geekbrains.ru.banananotes.model.Note

class NoteViewState(note: Note? = null, error: Throwable? = null) : BaseViewState<Note?>(note, error)