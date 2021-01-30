package geekbrains.ru.banananotes.ui.viewstate

import geekbrains.ru.banananotes.model.Note
import geekbrains.ru.banananotes.ui.viewstate.BaseViewState

class MainViewState(val notes: List<Note>? = null, error: Throwable? = null) :
    BaseViewState<List<Note>?>(notes, error)