package geekbrains.ru.banananotes.ui.viewstate

import geekbrains.ru.banananotes.model.Note
import geekbrains.ru.banananotes.ui.viewstate.BaseViewState

class NoteViewState(note: Note? = null, error: Throwable? = null) : BaseViewState<Note?>(note, error)