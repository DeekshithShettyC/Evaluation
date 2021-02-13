package com.robosoft.PhotosAndVideosApp.exception;

public class MobileAlreadyRegisteredException extends RuntimeException {
    public MobileAlreadyRegisteredException() {
        super();
    }

    public MobileAlreadyRegisteredException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public MobileAlreadyRegisteredException(final String message) {
        super(message);
    }

    public MobileAlreadyRegisteredException(final Throwable cause) {
        super(cause);
    }
}
