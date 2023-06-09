package com.example.bookcompose.di

import Repository
import org.koin.dsl.module

val repoModules = module {
    single { Repository(get()) }
}

