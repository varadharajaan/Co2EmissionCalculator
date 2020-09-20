package io.co2.backend.exception;

/**
 * @Author Varadharajan on 20/09/20 20:50
 * @Projectname co2-simulator
 */
public class InputCountMismatchException extends RuntimeException {
    public InputCountMismatchException(final String message) {
        super(message);
    }
}
