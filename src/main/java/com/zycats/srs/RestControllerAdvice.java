package com.zycats.srs;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zycats.srs.exception.InsufficientPriviledgesException;

@ControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class RestControllerAdvice {
	@ExceptionHandler(InsufficientPriviledgesException.class)
	public ResponseEntity<VndErrors> priviledgeException(final InsufficientPriviledgesException e) {
		return error(e, HttpStatus.UNAUTHORIZED, e.getEmployee().toString());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<VndErrors> assertionException(final IllegalArgumentException e) {
		return error(e, HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<VndErrors> noSuchElementException(final NoSuchElementException e) {
		return error(e, HttpStatus.NOT_FOUND, e.getLocalizedMessage());
	}

	private ResponseEntity<VndErrors> error(final Exception exception, final HttpStatus httpStatus,
			final String logRef) {
		final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
		return new ResponseEntity<>(new VndErrors(logRef, message), httpStatus);
	}
}