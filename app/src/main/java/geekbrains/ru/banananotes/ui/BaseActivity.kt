package geekbrains.ru.banananotes.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import geekbrains.ru.banananotes.R
import geekbrains.ru.banananotes.databinding.ActivityMainBinding
import geekbrains.ru.banananotes.model.Note
import geekbrains.ru.banananotes.viewmodel.BaseViewModel
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseActivity<T, VS : BaseViewState<T>> : AppCompatActivity() {
    abstract val viewModel: BaseViewModel<T, VS>
    abstract val layoutRes: Int
   // abstract val ui: ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(layoutRes)

        viewModel.getViewState().observe(this, object : Observer<VS> {
            override fun onChanged(t: VS?) {
                if (t == null) return
                if (t.data != null) renderData(t.data!!)
                if (t.error != null) renderError(t.error)
            }
        })
    }

    abstract fun renderData(data: T)

    protected fun renderError(error: Throwable) {
        if (error.message != null) showError(error.message!!)
    }

    protected fun showError(error: String) {
        val snackbar = Snackbar.make(mainRecycler, error, Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction(R.string.ok_bth_title) { snackbar.dismiss() }
        snackbar.show()
    }
}