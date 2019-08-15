package co.thrivemobile.bpt.account

import co.thrivemobile.bpt.R
import co.thrivemobile.bpt.account.items.SalutationItem
import co.thrivemobile.bpt.account.vm.SalutationViewModel
import co.thrivemobile.bpt.util.InstantExecutorExtension
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(value = [ InstantExecutorExtension::class, MockitoExtension::class ])
class SalutationViewModelTest {

    @Test
    @DisplayName("When no name is provided then show the generic salutation")
    fun noNameProvided() {
        val viewModel = SalutationViewModel()
        viewModel.setSalutation(SalutationItem())
        assertTrue(viewModel.salutationResLiveData.value == R.string.generic_salutation)
        assertNull(viewModel.userNameLiveData.value)
    }

    @Test
    @DisplayName("When name is provided then show the specific salutation")
    fun nameProvided() {
        val viewModel = SalutationViewModel()
        val name = "Name"
        viewModel.setSalutation(SalutationItem(name))
        assertTrue(viewModel.salutationResLiveData.value == R.string.specific_salutation)
        assertTrue(viewModel.userNameLiveData.value == name)
    }
}
