package com.issuetracker.issuetracker.config

import org.flywaydb.core.Flyway
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FlywayConfig {
    @Value("\${spring.flyway.url}")
    private val url: String? = null

    @Value("\${spring.flyway.user}")
    private val user: String? = null

    @Value("\${spring.flyway.password}")
    private val password: String? = null

    @Bean(initMethod = "migrate")
    fun flyway(): Flyway? {
        return Flyway(Flyway.configure()
                .baselineOnMigrate(true)
                .dataSource(url, user, password)
        )
    }
}