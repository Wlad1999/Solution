package com.youarelaunched.challenge.ui.screen.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youarelaunched.challenge.data.repository.VendorsRepository
import com.youarelaunched.challenge.data.repository.model.Vendor
import com.youarelaunched.challenge.ui.screen.state.VendorsScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VendorsVM @Inject constructor(
    private val repository: VendorsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        VendorsScreenUiState(
            vendors = null,
            query = null
        )
    )
    val uiState = _uiState.asStateFlow()

    init {
        getVendors(_uiState.value.query)
    }

    fun getVendors(query: String?) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    vendors = repository.getVendors(),
                    query = query
                )
            }

            if (!query.isNullOrBlank()) {
                val vendorsList: MutableList<Vendor> = mutableListOf()
                _uiState.value.vendors?.forEach { vendor ->
                    if (vendor.companyName.contains(query, ignoreCase = true)) {
                        vendorsList.add(vendor)
                    }
                }
                _uiState.update {
                    it.copy(
                        vendors = vendorsList,
                        query = query
                    )
                }
            }
        }
    }

}