package com.epam.jobmatch.dao.connection_pool.exception;


/**
 * Thrown when some kind of exceptional situation
 * during connection pool work has occurred
 */
public class ConnectionPoolException extends Exception {
    private static final long serialVersionUID = 1L;

    public ConnectionPoolException() {
        super();
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
