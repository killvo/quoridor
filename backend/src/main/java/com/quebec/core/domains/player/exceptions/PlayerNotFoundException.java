package com.quebec.core.domains.player.exceptions;

public class PlayerNotFoundException extends RuntimeException {
	public PlayerNotFoundException(String message) {
		super(message);
	}
}
