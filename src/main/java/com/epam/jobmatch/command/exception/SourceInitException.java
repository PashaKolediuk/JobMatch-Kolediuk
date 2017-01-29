package com.epam.jobmatch.command.exception;


/**
 * Thrown when some kind of exceptional situation
 * during data source initialization has occurred
 */
public class SourceInitException extends Exception {

    private static final long serialVersionUID = 1L;

    public SourceInitException(Throwable cause) {
        super(cause);
    }

    public SourceInitException(String message, Throwable cause) {
        super(message, cause);
    }

    public SourceInitException(String message) {
        super(message);
    }

    public SourceInitException() {
        super();
    }
}
