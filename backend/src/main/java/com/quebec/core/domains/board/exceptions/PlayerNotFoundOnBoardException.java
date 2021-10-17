package com.quebec.core.domains.board.exceptions;

public class PlayerNotFoundOnBoardException extends RuntimeException {
	public PlayerNotFoundOnBoardException(String message) {
		super(message);
	}
}
