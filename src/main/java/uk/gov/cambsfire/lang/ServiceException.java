package uk.gov.cambsfire.lang;

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ServiceException() {
        super();
    }

    public ServiceException(final String message, final Throwable cause, final boolean enableSuppression,
            final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ServiceException(final String message) {
        super(message);
    }

    public ServiceException(final Throwable cause) {
        super(cause);
    }

}
