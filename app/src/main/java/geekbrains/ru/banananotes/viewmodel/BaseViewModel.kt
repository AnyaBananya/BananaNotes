package geekbrains.ru.banananotes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import geekbrains.ru.banananotes.ui.viewstate.BaseViewState

open class BaseViewModel<T, VS : BaseViewState<T>>() : ViewModel() {
    open val viewStateLiveData = MutableLiveData<VS>()
    open fun getViewState(): LiveData<VS> = viewStateLiveData
}