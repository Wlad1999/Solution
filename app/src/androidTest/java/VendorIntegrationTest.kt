import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.youarelaunched.challenge.data.repository.model.Vendor
import com.youarelaunched.challenge.middle.R
import com.youarelaunched.challenge.ui.screen.state.VendorsScreenUiState
import com.youarelaunched.challenge.ui.screen.view.VendorsScreen
import com.youarelaunched.challenge.ui.theme.VendorAppTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VendorIntegrationTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun testContentVisible() {
        val state = VendorsScreenUiState(
            vendors = listOf(
                Vendor(
                    id = 0,
                    companyName = "",
                    coverPhoto = "",
                    area = "",
                    favorite = true,
                    categories = null,
                    tags = null
                )
            ),
            query = null
        )
        setUpComposerRole(state)

        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.vendor_item_tag))
            .assertIsDisplayed()
    }

    @Test
    fun testNoResult() {
        val state = VendorsScreenUiState(vendors = listOf(), query = null)
        setUpComposerRole(state)

        val noResultTitle = composeRule.activity.getString(R.string.empty_item_title)
        val noResultDescription =
            composeRule.activity.getString(R.string.empty_item_description)

        composeRule.onNodeWithText(noResultTitle).assertIsDisplayed()
        composeRule.onNodeWithText(noResultDescription).assertIsDisplayed()
    }

    private fun setUpComposerRole(uiState: VendorsScreenUiState) {
        composeRule.setContent {
            VendorAppTheme {
                VendorsScreen(
                    uiState = uiState,
                    clickOnSearch = {},
                )
            }
        }
    }
}