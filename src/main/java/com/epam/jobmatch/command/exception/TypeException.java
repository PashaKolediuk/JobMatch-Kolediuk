package com.epam.jobmatch.command.exception;


/**
 * Thrown when some kind of exceptional situation
 * during match type class has occurred
 */
public class TypeException extends Exception {

    private static final long serialVersionUID = 1L;

    public TypeException() { super();
    }

    public TypeException(String message) {
        super(message);
    }

    public TypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public TypeException(Throwable cause) {
        super(cause);
    }
}
