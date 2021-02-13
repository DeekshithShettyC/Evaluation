package com.robosoft.PhotosAndVideosApp.exception;

public class ProfileAlreadyExistException extends RuntimeException {


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ProfileAlreadyExistException() {
        super();
    }

    public ProfileAlreadyExistException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ProfileAlreadyExistException(final String message) {
        super(message);
    }

    public ProfileAlreadyExistException(final Throwable cause) {
        super(cause);
    }

}
