package co.thrivemobile.bpt

import co.thrivemobile.bpt.entry_form.EntryFormViewModel
import co.thrivemobile.bpt.statistics.StatisticsViewModel
import co.thrivemobile.repository.BptDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.threeten.bp.LocalDateTime

private val nowQualifier = named("now")

val appModule = module {

    single { BptDatabase.getInstance(androidContext()) }
    single { BptDatabase.getInstance(androidContext()).bptDao() }

    factory<() -> LocalDateTime>(nowQualifier) { { LocalDateTime.now()} }

    viewModel { StatisticsViewModel(get()) }
    viewModel { EntryFormViewModel(get(), get(nowQualifier)) }
}