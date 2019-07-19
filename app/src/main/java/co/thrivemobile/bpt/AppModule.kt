package co.thrivemobile.bpt

import co.thrivemobile.bpt.entry_form.EntryFormViewModel
import co.thrivemobile.bpt.info.InfoViewModel
import co.thrivemobile.bpt.statistics.StatisticsViewModel
import co.thrivemobile.bpt.statistics.vm.SparkViewModel
import co.thrivemobile.repository.BptDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.threeten.bp.LocalDateTime
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneId

private val nowOffsetDateTime = named("nowOffsetDateTime")

val appModule = module {

    single { BptDatabase.getInstance(androidContext()) }
    single { BptDatabase.getInstance(androidContext()).bptDao() }

    factory<() -> OffsetDateTime>(nowOffsetDateTime) { { OffsetDateTime.now(ZoneId.systemDefault()) } }

    viewModel { StatisticsViewModel(get(), get(nowOffsetDateTime)) }
    viewModel { EntryFormViewModel(get(), get(nowOffsetDateTime)) }
    viewModel { SparkViewModel(get(), get(nowOffsetDateTime)) }
    viewModel { InfoViewModel() }
}