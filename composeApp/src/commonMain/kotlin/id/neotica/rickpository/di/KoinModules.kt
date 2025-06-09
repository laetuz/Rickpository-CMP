package id.neotica.rickpository.di

import id.neotica.rickpository.data.CharacterRepositoryImpl
import id.neotica.rickpository.data.local.CharacterLocalDataSourceImpl
import id.neotica.rickpository.domain.remote.CharacterRepository
import id.neotica.rickpository.domain.local.CharacterLocalDataSource
import id.neotica.rickpository.networking.ktorModule
import id.neotica.rickpository.presentation.screen.characterdetail.CharacterDetailViewModel
import id.neotica.rickpository.presentation.screen.characters.CharactersViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module

val viewModelModules = module {
    viewModelOf(::CharactersViewModel)
    viewModelOf(::CharacterDetailViewModel)
}

val repositories = module {
    singleOf(::CharacterRepositoryImpl) bind CharacterRepository::class
    singleOf(::CharacterLocalDataSourceImpl).bind(CharacterLocalDataSource::class)
//    single<RickpositoryLocalDataSource> {???
//        RickpositoryLocalDataSourceImpl(get())
//    }
}

fun initializeKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            ktorModule,
            platformModule(),
            repositories,
            viewModelModules,
            provideDatabaseModule
//            platformModule(),
        )
    }
}