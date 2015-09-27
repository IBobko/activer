package ru.todo100.activer.util;

import java.util.LinkedList;

@SuppressWarnings("serial")
public class InputError extends Throwable{
	private LinkedList<String> errors = new LinkedList<String>();
	public LinkedList<String> getErrors() {
		return errors;
	}

	public void addError(String error) {
		this.errors.add(error);
	}
}
