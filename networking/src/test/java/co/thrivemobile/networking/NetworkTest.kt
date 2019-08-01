package co.thrivemobile.networking

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(value = [ MockitoExtension::class ])
class NetworkTest {

    @Mock lateinit var mockClient: OkHttpClient
    @ExperimentalCoroutinesApi private val testDispatcher = Dispatchers.Unconfined
    @ExperimentalCoroutinesApi private val network = Network(
        testDispatcher,
        testDispatcher,
        mockClient
    )

    @Test
    @DisplayName("When request comes back empty then we get default metadata")
    fun emptyNetwork() {
        
    }
}
