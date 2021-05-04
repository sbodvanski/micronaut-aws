/*
 * Copyright 2017-2020 original authors
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
package io.micronaut.function.aws.proxy.test;

import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.services.lambda.runtime.Context;
import io.micronaut.core.annotation.NonNull;

import javax.inject.Singleton;

/**
 * Provides a {@link MockLambdaContext}.
 *
 * @author Sergio del Amo
 */
@Singleton
public class MockContextProvider implements ContextProvider {

    /**
     *
     * @return a {@link MockLambdaContext}.
     */
    @NonNull
    @Override
    public Context getContext() {
        return new MockLambdaContext();
    }
}
