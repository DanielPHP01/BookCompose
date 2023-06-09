package com.example.bookcompose.di

import MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModules = module {
    viewModel { MainViewModel(get()) }
}
