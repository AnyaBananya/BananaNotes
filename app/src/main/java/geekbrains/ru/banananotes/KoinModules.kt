import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import geekbrains.ru.banananotes.model.provider.FireStoreProvider
import geekbrains.ru.banananotes.model.provider.RemoteDataProvider
import geekbrains.ru.banananotes.model.repository.Repository
import geekbrains.ru.banananotes.viewmodel.MainViewModel
import geekbrains.ru.banananotes.viewmodel.NoteViewModel
import geekbrains.ru.banananotes.viewmodel.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single { FireStoreProvider(get(), get()) } bind RemoteDataProvider::class
    single { Repository(get()) }
}

val splashModule = module {
    viewModel { SplashViewModel(get()) }
}
val mainModule = module {
    viewModel { MainViewModel(get()) }
}
val noteModule = module {
    viewModel { NoteViewModel(get()) }
}
