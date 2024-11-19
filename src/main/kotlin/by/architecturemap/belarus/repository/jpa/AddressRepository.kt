package by.architecturemap.belarus.repository.jpa

import by.architecturemap.belarus.entity.Address
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AddressRepository : JpaRepository<Address, Int>
