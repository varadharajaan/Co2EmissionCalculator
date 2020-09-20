package io.co2.backend.exception;

/**
 * @Author Varadharajan on 20/09/20 21:20
 * @Projectname co2-simulator
 */
public class RequiredInputParamsNotFoundException extends RuntimeException {

    public RequiredInputParamsNotFoundException(final String message) {
        super(message);
    }
}
