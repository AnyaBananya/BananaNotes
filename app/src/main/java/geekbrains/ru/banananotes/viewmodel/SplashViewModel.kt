package geekbrains.ru.banananotes.viewmodel

import geekbrains.ru.banananotes.model.NoAuthException
import geekbrains.ru.banananotes.model.Repository
import geekbrains.ru.banananotes.ui.SplashViewState

class SplashViewModel(private val repository: Repository = Repository) :
    BaseViewModel<Boolean?, SplashViewState>() {

    fun requestUser() {
        repository.getCurrentUser().observeForever { user ->
            viewStateLiveData.value = user?.let {
                SplashViewState(isAuth = true)
            } ?: SplashViewState(error = NoAuthException())
        }
    }
}