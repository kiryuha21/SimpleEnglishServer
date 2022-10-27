package com.example.simpleenglishserver

import org.jasypt.encryption.StringEncryptor
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class JasyptConfig {
    @Bean(name = ["jasyptStringEncryptor"])
    fun getPasswordEncryptor(): StringEncryptor {
        val encryptor = PooledPBEStringEncryptor()
        val config = SimpleStringPBEConfig()
        config.password = "RoflanPominki" // encryptor's private key
        config.algorithm = "PBEWithMD5AndDES"
        config.setKeyObtentionIterations("1000")
        config.setPoolSize("1")
        config.providerName = "SunJCE"
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator")
        config.stringOutputType = "base64"
        encryptor.setConfig(config)
        return encryptor
    }
}