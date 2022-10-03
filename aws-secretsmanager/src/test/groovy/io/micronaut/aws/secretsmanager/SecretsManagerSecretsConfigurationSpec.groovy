package io.micronaut.aws.secretsmanager

class SecretsManagerSecretsConfigurationSpec extends ApplicationContextSpecification {

    @Override
    Map<String, Object> getConfiguration() {
        super.configuration + [
                'aws.secretsmanager.enabled': true,
                'aws.secretsmanager.secrets': [
                        ["secret": "rds", "prefix": "atasources.default"],
                        ["secret": "rds_backup", "prefix": "datasources.backup"]
                ]
        ]
    }

    void "temp: get secret configuration get secrets"() {
        def bean = applicationContext.getBean(SecretsManagerSecretsConfiguration)

        expect:
        !bean.getSecrets().isEmpty()
    }
    
}
