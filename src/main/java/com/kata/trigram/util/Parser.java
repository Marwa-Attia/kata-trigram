package com.kata.trigram.util;

import java.util.Map;
import java.util.Set;

import com.kata.trigram.exceptions.InvalidFileNameException;
import com.kata.trigram.exceptions.UnparsableFileException;

public interface Parser {
	public Map<String, Set<String>> parseFile(String fileName) throws InvalidFileNameException, UnparsableFileException ;
}
