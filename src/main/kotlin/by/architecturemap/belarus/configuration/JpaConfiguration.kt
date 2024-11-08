package by.architecturemap.belarus.configuration

import jakarta.persistence.EntityManagerFactory
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories("by.architecturemap.belarus.repository.jpa")
open class JpaConfiguration {

    @Bean
    open fun entityManagerFactory(dataSource: DataSource): LocalContainerEntityManagerFactoryBean {
        val factoryBean = LocalContainerEntityManagerFactoryBean()
        factoryBean.dataSource = dataSource
        factoryBean.jpaVendorAdapter = HibernateJpaVendorAdapter()
        factoryBean.setPackagesToScan("by.architecturemap.belarus.entity")

        val jpaProperties = mutableMapOf<String, Any>()
        jpaProperties["hibernate.hbm2ddl.auto"] = "update"
        jpaProperties["hibernate.show_sql"] = "true"
        jpaProperties["hibernate.format_sql"] = "true"
        jpaProperties["hibernate.physical_naming_strategy"] = CamelCaseToUnderscoresNamingStrategy::class.java.name
        factoryBean.setJpaPropertyMap(jpaProperties)

        return factoryBean
    }

    @Bean
    open fun transactionManager(entityManagerFactory: EntityManagerFactory): JpaTransactionManager {
        return JpaTransactionManager(entityManagerFactory)
    }
}
