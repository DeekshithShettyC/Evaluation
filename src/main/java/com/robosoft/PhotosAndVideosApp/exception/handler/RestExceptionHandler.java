package com.robosoft.PhotosAndVideosApp.exception.handler;

import com.robosoft.PhotosAndVideosApp.exception.ApiError;
import com.robosoft.PhotosAndVideosApp.exception.MobileAlreadyRegisteredException;
import com.robosoft.PhotosAndVideosApp.exception.ProfileNotFoundException;
import com.robosoft.PhotosAndVideosApp.exception.RoleAlreadyPresent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MobileAlreadyRegisteredException.class)
    protected ResponseEntity<Object> handleMobileAlreadyRegistered(MobileAlreadyRegisteredException ex) {
        HttpStatus status = HttpStatus.CONFLICT;
        ApiError apiError = new ApiError(status);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError, status);
    }
    @ExceptionHandler(RoleAlreadyPresent.class)
    protected ResponseEntity<Object> RoleAlreadyPresentException(RoleAlreadyPresent ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiError apiError = new ApiError(status);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError, status);
    }

    @ExceptionHandler(ProfileNotFoundException.class)
    protected ResponseEntity<Object> handleFarmerProfileNotFound(ProfileNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiError apiError = new ApiError(status);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError, status);
    }
    private ResponseEntity<Object> buildResponseEntity(ApiError apiError,HttpStatus status) {
        return new ResponseEntity<Object>(apiError,status);
    }
}

