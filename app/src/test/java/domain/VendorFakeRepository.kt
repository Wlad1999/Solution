package domain

import com.youarelaunched.challenge.data.repository.VendorsRepository
import com.youarelaunched.challenge.data.repository.model.Vendor

class VendorFakeRepository : VendorsRepository {
        override suspend fun getVendors(): List<Vendor> =
            listOf(
                Vendor(
                    id= 0,
                    companyName = "",
                    coverPhoto = "",
                    area = "",
                    favorite = false,
                    categories = null,
                    tags = ""
                )
            )
    }