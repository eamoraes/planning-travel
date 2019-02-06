package br.com.planningtravel.advice;

import java.nio.file.NoSuchFileException;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.planningtravel.response.Response;


@RestControllerAdvice
public class PlanningTravelAdvice {
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<Response<?>> handlerException(Exception ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(Response.errors(ex.getMessage()));
	}

	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Response<?>> handlerNoSuchFileException(NoSuchFileException ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(Response.errors("commons.message.missing.folder"));
	}
}
