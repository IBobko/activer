package ru.todo100.activer.util;

import java.util.LinkedList;

public class InputError extends Throwable {
	private LinkedList<String> errors = new LinkedList<>();
	public LinkedList<String> getErrors() {
		return errors;
	}

	public void addError(String error) {
		this.errors.add(error);
	}
}
