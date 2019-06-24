package co.thrivemobile.bpt

import co.thrivemobile.bpt.statistics.StatisticsViewModel
import co.thrivemobile.repository.BptDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { BptDatabase.getInstance(androidContext()) }

    viewModel { StatisticsViewModel(get()) }
}