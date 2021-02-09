package geekbrains.ru.banananotes.ui

import geekbrains.ru.banananotes.model.Note

class MainViewState(val notes: List<Note>? = null, error: Throwable? = null) :
    BaseViewState<List<Note>?>(notes, error)