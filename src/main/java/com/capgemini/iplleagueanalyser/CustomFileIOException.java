package com.capgemini.iplleagueanalyser;

enum ExceptionTypeIO{
	FILE_PROBLEM
}

@SuppressWarnings("serial")
public class CustomFileIOException extends Exception {
	public CustomFileIOException(ExceptionTypeIO exceptionType) {
		super(exceptionType.toString());
	}
}
