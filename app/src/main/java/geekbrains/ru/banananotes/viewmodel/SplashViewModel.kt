package geekbrains.ru.banananotes.viewmodel

import geekbrains.ru.banananotes.model.NoAuthException
import geekbrains.ru.banananotes.model.repository.Repository
import geekbrains.ru.banananotes.ui.viewstate.SplashViewState
import kotlinx.coroutines.launch

class SplashViewModel(private val repository: Repository) :
    BaseViewModel<Boolean>() {

    fun requestUser() {
        launch {
            repository.getCurrentUser()?.let { setData(true) }
                ?: setError(NoAuthException())
        }
    }
}