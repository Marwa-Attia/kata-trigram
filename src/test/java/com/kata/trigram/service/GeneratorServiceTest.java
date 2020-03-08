package com.kata.trigram.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kata.trigram.exceptions.InvalidSeedMapException;
import com.kata.trigram.util.Constants;

public class GeneratorServiceTest {
	final static Logger LOGGER = Logger.getLogger(GeneratorServiceTest.class);
	private Map<String, Set<String>> dummySeedMap = new HashMap<String, Set<String>>();

	@BeforeEach
	public void initSeeMap() {
		dummySeedMap.clear();
		Set<String> values= new HashSet<String>();
		//I_wish->I
		values.add("I");
		dummySeedMap.put("I_wish", values);
		//wish_I->may,might
		values=new HashSet<String>();
		values.add("may");
		values.add("might");
		dummySeedMap.put("wish_I",values );
		//I_may->I
		values=new HashSet<String>();
		values.add("I");
		dummySeedMap.put("I_may",values );
		//may_I->wish
		values=new HashSet<String>();
		values.add("wish");
		dummySeedMap.put("may_I",values );

	}
	@Test
	public void sendingNullSeedMapShouldThrowInvalidSeedMapException() {

		GenratorService service = new GenratorService();
		Exception exception = assertThrows(InvalidSeedMapException.class, () -> {
			service.generateText(null);
		});

	}
	@Test
	public void sendingEmptySeedMapShouldThrowInvalidSeedMapException() {

		GenratorService service = new GenratorService();
		Exception exception = assertThrows(InvalidSeedMapException.class, () -> {
			service.generateText(new HashMap<String, Set<String>>());
		});

	}

	@Test
	public void sendingnonEmptySeedMapShouldGenerateTheFileCorrectly() {
		GenratorService service = new GenratorService();
		try {
			String fileName = service.generateText(dummySeedMap);
			assertNotNull(fileName);
			File file = new File(Constants.BASE_URL.getPath() + fileName);
			assertTrue(file.exists());

		} catch (InvalidSeedMapException e) {
			LOGGER.error(e.getLocalizedMessage());
		}
		

	}

}
