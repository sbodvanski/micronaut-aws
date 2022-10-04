/*
 * Copyright 2017-2021 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.aws.secretsmanager;

import io.micronaut.aws.distributedconfiguration.AwsDistributedConfiguration;
import io.micronaut.aws.distributedconfiguration.AwsDistributedConfigurationClient;
import io.micronaut.context.annotation.BootstrapContextCompatible;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.core.util.StringUtils;
import io.micronaut.runtime.ApplicationConfiguration;

import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Distributed configuration client for AWS Secrets Manager.
 * @see <a href="https://aws.amazon.com/secrets-manager/">AWS Secrets Manager</a>
 * @author Sergio del Amo
 * @since 2.8.0
 */
@Requires(beans = {
    AwsDistributedConfiguration.class,
    SecretsManagerKeyValueFetcher.class
})
@Singleton
@BootstrapContextCompatible
public class SecretsManagerConfigurationClient extends AwsDistributedConfigurationClient {

    /**
     * @param awsDistributedConfiguration AWS Distributed Configuration
     * @param secretsManagerKeyValueFetcher Secrets Manager Key Value Fetcher
     * @param applicationConfiguration Application Configuration
     * @param secretsManagerSecretsConfiguration
     */
    public SecretsManagerConfigurationClient(AwsDistributedConfiguration awsDistributedConfiguration,
                                             SecretsManagerKeyValueFetcher secretsManagerKeyValueFetcher,
                                             @Nullable ApplicationConfiguration applicationConfiguration,
                                             SecretsManagerSecretsConfiguration secretsManagerSecretsConfiguration) {
        super(awsDistributedConfiguration, secretsManagerKeyValueFetcher, applicationConfiguration);
        this.secretsManagerSecretsConfiguration = secretsManagerSecretsConfiguration;
    }

    private SecretsManagerSecretsConfiguration secretsManagerSecretsConfiguration;

    @Override
    @NonNull
    protected String getPropertySourceName() {
        return "awssecretsmanager";
    }

    @Override
    public String getDescription() {
        return "AWS Secrets Manager";
    }


    @Override
    @NonNull
    protected Map<String, Optional<String>> generateConfigurationResolutionPrefixes(@NonNull Environment environment) {
        Map<String, Optional<String>> configurationResolutionPrefixes = super.generateConfigurationResolutionPrefixes(environment);
        List<SecretsManagerSecretsConfiguration.SecretHolder> secretConfiguration = secretsManagerSecretsConfiguration.getSecrets();
        Map<String, Optional<String>> result = new HashMap<>();

        for (String configurationResolutionPrefix : configurationResolutionPrefixes.keySet()) {
            for (SecretsManagerSecretsConfiguration.SecretHolder secretHolder : secretConfiguration) {
                if (StringUtils.isNotEmpty(secretHolder.getSecret())) {
                    String secretResolutionPrefix = configurationResolutionPrefix + secretHolder.getSecret();
                    result.put(secretResolutionPrefix, Optional.of(secretHolder.getPrefix()));
                }
            }
        }

        result.putAll(configurationResolutionPrefixes);
        return result;
    }
}
