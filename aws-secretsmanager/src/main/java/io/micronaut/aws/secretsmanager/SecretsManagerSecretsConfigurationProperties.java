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

import io.micronaut.context.annotation.BootstrapContextCompatible;
import io.micronaut.context.annotation.ConfigurationProperties;

import java.util.List;

/**
 * {@link ConfigurationProperties} implementation of {@link SecretsManagerConfiguration}.
 *
 * @author Sergio del Amo
 * @since 2.8.0
 */
@BootstrapContextCompatible
@ConfigurationProperties(SecretsManagerConfigurationProperties.PREFIX)
public class SecretsManagerSecretsConfigurationProperties implements SecretsManagerSecretsConfiguration {

    private List<SecretHolder> secrets;

    /**
     * Sets the secret configuration.
     *
     * @param secrets the secret configuration.
     */
    public void setSecrets(List<SecretHolder> secrets) {
        this.secrets = secrets;
    }

    /**
     * @return the AWS Secrets Manager secrets configuration.
     */
    @Override
    public List<SecretHolder> getSecrets() {
        return secrets;
    }
}
