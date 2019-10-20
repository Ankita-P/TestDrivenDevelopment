package com.aseassignments.isbntools;

import static org.junit.Assert.*;

import org.junit.Test;

import com.aseassignment.isbntools.ValidateISBN;

public class ValidateISBNTest {

	@Test
	public void checkAValid10DigitISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("0140449116");
		assertTrue("first value", result);
		result = validator.checkISBN("0140177396");
		assertTrue("second value", result);
	}
	
	
	@Test
	public void checkAValid13DigitISBN()
	{
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("9781853260087");
		assertTrue("first value", result);
		result = validator.checkISBN("9781853267338");
		assertTrue("second value", result);
	}
	
	@Test
	public void TenDigitIsbnNumberEndingWithXAreValid()
	{
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("012000030X");
		assertTrue( result);
	}
	
	@Test
	public void checkAnIValid10DigitISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("0140449117");
		assertFalse(result);
	}
	
	@Test
	public void checkAnIValid13DigitISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("9781853260084");
		assertFalse(result);
		
	}
	
	@Test(expected = NumberFormatException.class)
	public void nineDigitISBNNotAllowed()
	{
		ValidateISBN validator = new ValidateISBN();
		validator.checkISBN("123456789");
	}

	@Test(expected = NumberFormatException.class)
	public void nonNumericISBNNotAllowed()
	{
		ValidateISBN validator = new ValidateISBN();
		validator.checkISBN("helloworld");
	}
	
	
}
