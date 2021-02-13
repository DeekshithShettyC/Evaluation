package com.robosoft.PhotosAndVideosApp.exception;


public class ProfileNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1919229614551847831L;

    public ProfileNotFoundException() {
        super();
    }

    public ProfileNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ProfileNotFoundException(final String message) {
        super(message);
    }

    public ProfileNotFoundException(final Throwable cause) {
        super(cause);
    }
}