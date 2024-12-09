package com.application.desafio_shopper.di

import com.application.desafio_shopper.repository.RetrofitRepository
import com.application.desafio_shopper.viewmodel.ChooseTripViewModel
import com.application.desafio_shopper.viewmodel.HistoryTripViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { RetrofitRepository() }
    viewModel { ChooseTripViewModel() }
    viewModel { HistoryTripViewModel() }
}