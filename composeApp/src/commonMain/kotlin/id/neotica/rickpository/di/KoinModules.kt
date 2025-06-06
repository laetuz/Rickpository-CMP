package id.neotica.rickpository.di

import id.neotica.rickpository.networking.ktorModule
import id.neotica.rickpository.presentation.screen.characterdetail.CharacterDetailViewModel
import id.neotica.rickpository.presentation.screen.characters.CharactersViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModules = module {
    viewModelOf(::CharactersViewModel)
    viewModelOf(::CharacterDetailViewModel)
}

fun initializeKoin() {
    startKoin { modules(ktorModule, viewModelModules) }
}