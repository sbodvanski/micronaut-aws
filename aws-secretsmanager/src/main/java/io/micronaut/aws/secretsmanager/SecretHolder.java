package io.micronaut.aws.secretsmanager;

// TODO: Add javadoc!

/**
 * *
 */
public class SecretHolder {
    // TODO: Better naming to avoid ambiguity.
    private String secret;

    private String prefix;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
