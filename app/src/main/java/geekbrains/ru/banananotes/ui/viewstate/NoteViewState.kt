package geekbrains.ru.banananotes.ui.viewstate

import geekbrains.ru.banananotes.model.Note
import geekbrains.ru.banananotes.ui.viewstate.BaseViewState

class NoteViewState(data: Data = Data(), error: Throwable? = null) :
    BaseViewState<NoteViewState.Data>(data, error) {

    data class Data(val isDeleted: Boolean = false, val note: Note? = null)
}