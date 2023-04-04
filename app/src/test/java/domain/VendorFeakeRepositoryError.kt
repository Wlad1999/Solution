package domain

import com.youarelaunched.challenge.data.repository.VendorsRepository
import com.youarelaunched.challenge.data.repository.model.Vendor

class VendorFakeRepositoryError : VendorsRepository {
    override suspend fun getVendors(): List<Vendor> = throw Exception()
}