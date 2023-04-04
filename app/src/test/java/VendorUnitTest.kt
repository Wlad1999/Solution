import com.youarelaunched.challenge.ui.screen.view.VendorsVM
import domain.VendorFakeRepository
import domain.VendorFakeRepositoryError
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class VendorUnitTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: VendorsVM

    @Test
    fun data_was_loaded_successfully_and_the_list_contains_at_least_one_item() = runTest {
        viewModel = VendorsVM(VendorFakeRepository())
        viewModel.getVendors(null)

        assertNotNull(viewModel.uiState.first().vendors.isNullOrEmpty())
    }

    @Test
    fun data_was_loaded_with_error() = runTest {
        viewModel = VendorsVM(VendorFakeRepositoryError())
        viewModel.getVendors(null)

        assertNull(viewModel.uiState.first().vendors)
    }
}


@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}