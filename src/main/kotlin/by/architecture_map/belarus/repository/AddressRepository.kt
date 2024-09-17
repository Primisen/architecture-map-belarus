package by.architecture_map.belarus.repository

import by.architecture_map.belarus.entity.Address
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AddressRepository : JpaRepository<Address, Int>