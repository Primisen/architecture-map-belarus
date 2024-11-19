package by.architecturemap.belarus.configuration

import jakarta.persistence.EntityManagerFactory
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.orm.jpa.JpaTransactionManager

@DataJpaTest
@Import(JpaConfiguration::class)
open class JpaConfigurationTest {

    @Autowired
    private lateinit var entityManagerFactory: EntityManagerFactory

    @Autowired
    private lateinit var transactionManager: JpaTransactionManager

    @Test
    fun entityManagerFactoryShouldBeConfigured() {
        // Verify that entityManagerFactory is correctly configured
        assertNotNull(entityManagerFactory)
    }

    @Test
    fun transactionManagerShouldBeConfigured() {
        // Verify that transactionManager is correctly configured
        assertNotNull(transactionManager)
    }
}
