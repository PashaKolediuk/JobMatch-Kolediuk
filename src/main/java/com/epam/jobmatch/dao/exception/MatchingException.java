package com.epam.jobmatch.dao.exception;

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
