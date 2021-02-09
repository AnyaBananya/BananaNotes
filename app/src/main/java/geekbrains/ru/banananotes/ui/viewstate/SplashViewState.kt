package geekbrains.ru.banananotes.ui.viewstate

import geekbrains.ru.banananotes.ui.viewstate.BaseViewState

class SplashViewState(isAuth: Boolean? = null, error: Throwable? = null) :
    BaseViewState<Boolean?>(isAuth, error)