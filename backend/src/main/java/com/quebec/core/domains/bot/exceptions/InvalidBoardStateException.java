package com.quebec.core.domains.bot.exceptions;

public class InvalidBoardStateException extends RuntimeException {
	public InvalidBoardStateException(String message) {
		super(message);
	}
}
