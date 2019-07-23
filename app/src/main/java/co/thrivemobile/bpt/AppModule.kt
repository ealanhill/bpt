package co.thrivemobile.bpt

import co.thrivemobile.bpt.entry_form.EntryFormViewModel
import co.thrivemobile.bpt.info.InfoViewModel
import co.thrivemobile.bpt.info.vm.ArticleViewModel
import co.thrivemobile.bpt.statistics.StatisticsViewModel
import co.thrivemobile.bpt.statistics.vm.SparkViewModel
import co.thrivemobile.networking.Network
import co.thrivemobile.repository.BptDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneId

private val nowOffsetDateTime = named("nowOffsetDateTime")

val appModule = module {

    single { BptDatabase.getInstance(androidContext()) }
    single { BptDatabase.getInstance(androidContext()).bptDao() }
    single { Network() }
    single { arrayOf(
        "https://alifeofproductivity.com/calculate-biological-prime-time/",
        "https://collegeinfogeek.com/track-body-energy-focus-levels/",
        "https://blog.trello.com/find-productive-hours",
        "https://www.thesimpledollar.com/managing-the-natural-ups-and-downs-of-your-workweek/",
        "https://endpoints.elysiumhealth.com/the-complete-guide-to-the-science-of-circadian-rhythms-7b78581cbffa"
    ) }

    factory<() -> OffsetDateTime>(nowOffsetDateTime) { { OffsetDateTime.now(ZoneId.systemDefault()) } }

    viewModel { StatisticsViewModel(get(), get(nowOffsetDateTime)) }
    viewModel { EntryFormViewModel(get(), get(nowOffsetDateTime)) }
    viewModel { SparkViewModel(get(), get(nowOffsetDateTime)) }
    viewModel { InfoViewModel(get()) }
    viewModel { ArticleViewModel(get()) }
}