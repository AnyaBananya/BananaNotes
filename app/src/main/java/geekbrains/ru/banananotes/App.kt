package geekbrains.ru.banananotes

import androidx.multidex.MultiDexApplication
import appModule
import mainModule
import noteModule
import org.koin.core.context.startKoin
import splashModule

class App: MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule, splashModule, mainModule, noteModule)
        }
    }
}