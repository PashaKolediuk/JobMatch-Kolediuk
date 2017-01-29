package com.epam.jobmatch.dao.exception;


/**
 * Thrown when some kind of exceptional situation
 * during data editing has occurred
 */
public class MatchingException extends Exception {

    private static final long serialVersionUID = 1L;

    public MatchingException() {
        super();
    }

    public MatchingException(String message) {
        super(message);
    }

    public MatchingException(String message, Throwable cause) {
        super(message, cause);
    }

    public MatchingException(Throwable cause) {
        super(cause);
    }
}
