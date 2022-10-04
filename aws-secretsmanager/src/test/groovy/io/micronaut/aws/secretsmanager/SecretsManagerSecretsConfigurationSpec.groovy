package io.micronaut.aws.secretsmanager

import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject

@MicronautTest
class SecretsManagerSecretsConfigurationSpec extends ApplicationContextSpecification {

    @Inject
    SecretsManagerSecretsConfiguration secretsConfiguration

    void "Make sure the config has secrets"() {

        expect:
        !secretsConfiguration.getSecrets().isEmpty()
    }

}
