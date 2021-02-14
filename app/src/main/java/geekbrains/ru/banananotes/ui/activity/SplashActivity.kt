package geekbrains.ru.banananotes.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.firebase.ui.auth.AuthUI
import geekbrains.ru.banananotes.R
import geekbrains.ru.banananotes.databinding.ActivitySplashBinding
import geekbrains.ru.banananotes.model.NoAuthException
import geekbrains.ru.banananotes.ui.viewstate.SplashViewState
import geekbrains.ru.banananotes.viewmodel.SplashViewModel
import org.koin.android.viewmodel.ext.android.viewModel

private const val RC_SING_IN = 42
private const val START_DELAY = 1000L

class SplashActivity : BaseActivity<Boolean>() {
    override val viewModel: SplashViewModel by viewModel()
    override val layoutRes: Int = R.layout.activity_splash
    override val ui: ActivitySplashBinding by lazy { ActivitySplashBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()
        Handler(Looper.getMainLooper()).postDelayed({ viewModel.requestUser() }, START_DELAY)
    }

    override fun renderData(data: Boolean) {
        if (data) startMainActivity()
    }

    override fun renderError(error: Throwable) {
        when (error) {
            is NoAuthException -> startLoginActivity()
            else -> error?.message?.let { showError(it) }
        }
    }

    private fun startLoginActivity() {
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setLogo(R.drawable.common_google_signin_btn_icon_dark_focused)
                .setTheme(R.style.LoginStyle)
                .setAvailableProviders(
                    listOf(
                        AuthUI.IdpConfig.EmailBuilder().build(),
                        AuthUI.IdpConfig.GoogleBuilder().build()
                    )
                ).build(), RC_SING_IN
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SING_IN && resultCode != Activity.RESULT_OK) {
            finish()
        }
    }

    private fun startMainActivity() {
        startActivity(MainActivity.getStartIntent(this))
        finish()
    }

    override fun onLogout() {
        //
    }
}