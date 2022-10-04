package io.micronaut.aws.secretsmanager;

import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.TypeConverter;
import jakarta.inject.Singleton;

import java.util.Map;
import java.util.Optional;

@Singleton
public class MapToSecretHolderConverter implements TypeConverter<Map, SecretsManagerSecretsConfiguration.SecretHolder> {

    @Override
    public Optional<SecretsManagerSecretsConfiguration.SecretHolder> convert(Map object, Class<SecretsManagerSecretsConfiguration.SecretHolder> targetType, ConversionContext context) {
        return Optional.of(new SecretsManagerSecretsConfiguration.SecretHolder() {
            @Override
            public String getSecret() {
                return object.getOrDefault("secret", "").toString();
            }

            @Override
            public String getPrefix() {
                return object.getOrDefault("prefix", "").toString();
            }
        });
    }
}
