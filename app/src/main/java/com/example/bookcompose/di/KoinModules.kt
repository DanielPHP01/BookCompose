package com.example.bookcompose.di

import com.example.bookcompose.remote.remoteDataSource
import org.koin.core.module.Module

val koinModules = listOf<Module>(
    repoModules,
    viewModules,
    remoteDataSource,
    networkModule
)