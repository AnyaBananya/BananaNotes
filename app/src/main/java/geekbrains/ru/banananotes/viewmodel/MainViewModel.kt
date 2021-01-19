package geekbrains.ru.banananotes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import geekbrains.ru.banananotes.model.Note
import geekbrains.ru.banananotes.model.Repository
import geekbrains.ru.banananotes.ui.MainViewState

class MainViewModel : ViewModel() {
    private val viewStateLiveData: MutableLiveData<MainViewState> = MutableLiveData()

    init {
        viewStateLiveData.value = MainViewState(Repository.getNotes())
    }

    fun viewState(): LiveData<MainViewState> = viewStateLiveData

    fun addNote(title: String, note: String, color: Int) {
        Repository.addNote(Note(title, note, color))
        viewStateLiveData.value = MainViewState(Repository.getNotes())
    }
}