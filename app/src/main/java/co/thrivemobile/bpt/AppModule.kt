package co.thrivemobile.bpt

import android.os.Build
import android.text.Html
import co.thrivemobile.bpt.account.vm.SalutationViewModel
import co.thrivemobile.bpt.account.vm.SettingsViewModel
import co.thrivemobile.bpt.entry_form.EntryFormViewModel
import co.thrivemobile.bpt.info.InfoViewModel
import co.thrivemobile.bpt.info.vm.ArticleViewModel
import co.thrivemobile.bpt.repository.Repository
import co.thrivemobile.bpt.statistics.StatisticsViewModel
import co.thrivemobile.bpt.statistics.vm.SparkViewModel
import co.thrivemobile.networking.Network
import co.thrivemobile.repository.BptDatabase
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneId

private val nowOffsetDateTime = named("nowOffsetDateTime")
private val decodeHtmlCharacters = named("decodeHtmlCharacters")
private val ioDispatcher = named("ioDispatcher")
private val mainDispatcher = named("mainDispatcher")
private val okHttpClient = named("OkHttpClient")
private val network = named("Network")
private val dao = named("DAO")
private val repository = named("Repository")

val appModule = module {

    single { BptDatabase.getInstance(androidContext()) }
    single(dao) { BptDatabase.getInstance(androidContext()).bptDao() }

    single(mainDispatcher) { Dispatchers.Main }
    single(ioDispatcher) { Dispatchers.IO }
    single(okHttpClient) {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        return@single OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    single(network) { Network(get(mainDispatcher), get(ioDispatcher), get(okHttpClient)) }
    single { arrayOf(
        "https://alifeofproductivity.com/calculate-biological-prime-time/",
        "https://collegeinfogeek.com/track-body-energy-focus-levels/",
        "https://blog.trello.com/find-productive-hours",
        "https://www.thesimpledollar.com/managing-the-natural-ups-and-downs-of-your-workweek/",
        "https://endpoints.elysiumhealth.com/the-complete-guide-to-the-science-of-circadian-rhythms-7b78581cbffa"
    ) }
    single<(String) -> String>(decodeHtmlCharacters) { { html ->
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            Html.fromHtml(html)
        } else {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        }.toString()
    } }

    single(repository) { Repository(get(ioDispatcher), get(dao), get(network)) }

    factory<() -> OffsetDateTime>(nowOffsetDateTime) { { OffsetDateTime.now(ZoneId.systemDefault()) } }

    viewModel { StatisticsViewModel(get(dao), get(nowOffsetDateTime)) }
    viewModel { EntryFormViewModel(get(dao), get(nowOffsetDateTime)) }
    viewModel { SparkViewModel(get(dao), get(nowOffsetDateTime)) }
    viewModel { InfoViewModel(get()) }
    viewModel { ArticleViewModel(get(repository), get(decodeHtmlCharacters)) }
    viewModel { SalutationViewModel() }
    viewModel { SettingsViewModel(get(dao)) }
}