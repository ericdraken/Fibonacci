/*
 * Copyright (c) 2019. Eric Draken - ericdraken.com
 */

package com.ericdraken.interviews;

import static com.ericdraken.interviews.ValidatePositive.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Validate the hint system if a string representation of an integer
 * is not a positive integer (greater than zero). There are some tricky
 * inputs like Indian numerals that could sneak past other validators.
 */
class ValidatePositiveTest
{
	// Zero-width whitespace characters
	// REF: https://stackoverflow.com/a/21797208/1938889
	private final static String INVISIBLE_WHITESPACE = "123" + Character.toString( (char)8203 ) + "456";

	private static String[] validStrings()
	{
		return new String[]{
			"1",
			"10",
			"100",
			"89038450983409580934850934850834",
			String.valueOf( Integer.MAX_VALUE )
		};
	}

	private static String[][] invalidStringsWithHints()
	{
		// The hints below are strings from ValidateNumeric
		return new String[][]{
			{"", EMPTY},
			{" ", WHITESPACE},
			{INVISIBLE_WHITESPACE, ASCII}, // Invisible space is not considered a space by Java
			{"abc", MIXED},
			{"100c", MIXED},
			{"--100", MIXED},
			{"100-000", MIXED},
			{"100-", MIXED},
			{"07", ZERO},
			{"007", ZERO},
			{"1.0", FRACTION},
			{"123E234", MIXED},
			{"-0", MIXED},
			{"-", MIXED},
			{"- ", MIXED},
			{"- 123", MIXED},
			{" -123", WHITESPACE},
			{".", FRACTION},
			{"१२३४५६७८९", ASCII}, // Indian numerals are considered digits by Character.isDigit()!
			{"一二", ASCII}
		};
	}

	@ParameterizedTest
	@MethodSource( value = "validStrings" )
	void isValidIntegerRepresentation_valid( String number )
	{
		assertTrue( ValidatePositive.isValidPositiveIntegerRepresentation( number ) );
	}

	@ParameterizedTest
	@MethodSource( value = "invalidStringsWithHints" )
	void isValidIntegerRepresentation_invalid( String number, String ignored )	// Ignore the hint
	{
		assertFalse( ValidatePositive.isValidPositiveIntegerRepresentation( number ) );
	}

	@ParameterizedTest
	@MethodSource( value = "invalidStringsWithHints" )
	void validateWithHints( String number, String hint )
	{
		assertEquals( hint, ValidatePositive.validateWithHints( number ) );
	}
}