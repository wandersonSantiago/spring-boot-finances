package com.api.personal.finance.exception.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.api.personal.finance.exception.NotFoundException;

import lombok.Getter;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String messageUser = messageSource.getMessage("message.invalid", null, LocaleContextHolder.getLocale());
		String messageDev = ex.getMessage();
		List<Error> errorList = Arrays.asList(new Error(messageUser, messageDev));
		return handleExceptionInternal(ex, errorList, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Error> errorList = createErrorList(ex.getBindingResult());

		return handleExceptionInternal(ex, errorList, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request){
		String messageUser = messageSource.getMessage("resource.not-found", null, LocaleContextHolder.getLocale());
		String messageDev = ex.getMessage();
		List<Error> errorList = Arrays.asList(new Error(messageUser, messageDev));
		return handleExceptionInternal(ex, errorList, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({NotFoundException.class})
	public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request){
		String messageUser = ex.getMessage();
		String messageDev = ex.toString();
		List<Error> errorList = Arrays.asList(new Error(messageUser, messageDev));
		return handleExceptionInternal(ex, errorList, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({ DataIntegrityViolationException.class } )
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
		String messageUser = messageSource.getMessage("resource.unathorized-operation", null, LocaleContextHolder.getLocale());
		String messageDev = ExceptionUtils.getRootCauseMessage(ex);
		List<Error> errorList = Arrays.asList(new Error(messageUser, messageDev));
		return handleExceptionInternal(ex, errorList, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	private List<Error> createErrorList(BindingResult bindingResult) {
		List<Error> list = new ArrayList<Error>();

		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String messageUser = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String messageDev = fieldError.toString();
			list.add(new Error(messageUser,messageDev));
		}
		return list;
	}

	private static class Error {

		@Getter
		private String messageUser;
		@Getter
		private String messageDev;

		public Error(String messageUser, String messageDev) {
			super();
			this.messageUser = messageUser;
			this.messageDev = messageDev;
		}
	}
}
