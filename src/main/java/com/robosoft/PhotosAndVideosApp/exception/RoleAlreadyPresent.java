package com.robosoft.PhotosAndVideosApp.exception;

public class RoleAlreadyPresent extends RuntimeException {
    public RoleAlreadyPresent() {
        super();
    }

    public RoleAlreadyPresent(final String message, final Throwable cause) {
        super(message, cause);
    }

    public RoleAlreadyPresent(final String message) {
        super(message);
    }

    public RoleAlreadyPresent(final Throwable cause) {
        super(cause);
    }
}
