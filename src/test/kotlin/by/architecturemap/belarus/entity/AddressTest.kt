package by.architecturemap.belarus.entity

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AddressTest {

    @Test
    fun testSettersAndGettersForAddress() {
        // arrange
        val expectedRegion = "Region"
        val expectedLocality = "test"
        val expectedDistrict = "test"
        val expectedStreet = "test"
        val expectedHouseNumber = "test"

        val address = Address(region = expectedRegion)

        // act
        address.region = expectedRegion
        address.locality = expectedLocality
        address.district = expectedDistrict
        address.street = expectedStreet
        address.houseNumber = expectedHouseNumber

        // assert
        assertEquals(expectedRegion, address.region)
        assertEquals(expectedLocality, address.locality)
        assertEquals(expectedDistrict, address.district)
        assertEquals(expectedStreet, address.street)
        assertEquals(expectedHouseNumber, address.houseNumber)
    }
}
