package by.architecturemap.belarus.dto

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ConstructionSearchingDTOTest {

    @Test
    fun testConstructorForConstructionSearchingDTO() {
        // arrange
        val architecturalStyleId = "123"
        val region = "RegionTest"
        val district = "DistrictTest"
        val buildingCenturyFrom = "10"
        val buildingCenturyTo = "15"

        // act
        val dto = ConstructionSearchingDTO(
            architecturalStyleId,
            region,
            district,
            buildingCenturyFrom,
            buildingCenturyTo
        )

        // assert
        assertEquals(architecturalStyleId, dto.architecturalStyleId)
        assertEquals(region, dto.region)
        assertEquals(district, dto.district)
        assertEquals(buildingCenturyFrom, dto.buildingCenturyFrom)
        assertEquals(buildingCenturyTo, dto.buildingCenturyTo)
    }

    @Test
    fun testSettersAndGettersForConstructionSearchingDTO() {
        // arrange
        val dto = ConstructionSearchingDTO(
            "123", "RegionTest", "DistrictTest", "10", "15"
        )

        // act
        dto.architecturalStyleId = "456"
        dto.region = "NewRegion"
        dto.district = "NewDistrict"
        dto.buildingCenturyFrom = "12"
        dto.buildingCenturyTo = "18"

        // assert
        assertEquals("456", dto.architecturalStyleId)
        assertEquals("NewRegion", dto.region)
        assertEquals("NewDistrict", dto.district)
        assertEquals("12", dto.buildingCenturyFrom)
        assertEquals("18", dto.buildingCenturyTo)
    }

    @Test
    fun testConstructorWithoutParametersOfConstructionSearchingDTO() {
        // act
        val dto = ConstructionSearchingDTO()

        // assert
        assertEquals(null, dto.region)
        assertEquals(null, dto.district)
        assertEquals(null, dto.architecturalStyleId)
        assertEquals(null, dto.buildingCenturyFrom)
        assertEquals(null, dto.buildingCenturyTo)
    }
}
