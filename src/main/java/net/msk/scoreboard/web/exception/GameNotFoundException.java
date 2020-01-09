package net.msk.scoreboard.web.exception;

public class GameNotFoundException extends RuntimeException {
	
	public GameNotFoundException() {
		super();
	}
	
	public GameNotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
