package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.entity.Role
import by.architecturemap.belarus.repository.jpa.RoleRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class RoleServiceImplTest {

    private val roleRepository: RoleRepository = mockk()
    private val roleServiceImpl = RoleServiceImpl(roleRepository)

    @Test
    fun givenRoleName_whenFindRole_thenReturnName() {

        //arrange
        val roleName = "USER"
        val expected = Role(name = roleName)
        every { roleRepository.findRoleByName(roleName) } returns expected

        //act
        val actual = roleServiceImpl.find(roleName)

        //assert
        assertEquals(expected, actual)
    }
}
