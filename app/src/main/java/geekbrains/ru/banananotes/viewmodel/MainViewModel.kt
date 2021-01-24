package geekbrains.ru.banananotes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import geekbrains.ru.banananotes.model.Note
import geekbrains.ru.banananotes.model.Repository
import geekbrains.ru.banananotes.ui.MainViewState

import java.util.*

class MainViewModel : ViewModel() {
    private val viewStateLiveData: MutableLiveData<MainViewState> = MutableLiveData()


    init {
        Repository.getNotes().observeForever { notes ->
            viewStateLiveData.value =
                viewStateLiveData.value?.copy(notes = notes) ?: MainViewState(notes)
        }
    }

    fun viewState(): LiveData<MainViewState> = viewStateLiveData

//    fun addNote(title: String, note: String, color: Int) {
//        Repository.addNote(Note(UUID.randomUUID().toString(), title, note, Color.PINK))
//        viewStateLiveData.value = MainViewState(Repository.getNotes())
//    }
}
