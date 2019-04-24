/*
 * Copyright (c) 2019. Eric Draken - ericdraken.com
 */

package com.ericdraken.interviews;

/**
 * String integer representation validator of positive integers with error hinting
 */
public class ValidatePositive
{
	final static String EMPTY = "The number is empty.";

	final static String WHITESPACE = "A valid number doesn't contain whitespace";

	final static String ZERO = "Positive integers start from 1";

	final static String FRACTION = "Fractional numbers are not supported.";

	final static String ASCII = "Only ASCII characters are allowed.";

	final static String MIXED = "Only the numerals 0-9 are valid.";

	final static String INVALID = "The number is invalid.";

	/**
	 * If the number is an invalid positive integer representation, return a hint
	 * as to why it fails, otherwise return null
	 *
	 * @param number Integer as a string
	 * @return A hint as to why the string is not a positive integer
	 */
	public static String validateWithHints( String number )
	{
		if ( ! isValidPositiveIntegerRepresentation( number ) )
		{
			// Empty cases
			if ( number.isEmpty() )
			{
				return EMPTY;
			}

			// Hint things like 01, 0.12, etc.
			if ( number.charAt( 0 ) == '0' )
			{
				return ZERO;
			}

			// Simple for-loop over the characters
			// to find the offending character
			for ( char chr : number.toCharArray() )
			{
				if ( chr == '.' )
				{
					return FRACTION;
				}
				if ( chr >= 128 )
				{
					return ASCII;
				}
				if ( chr == ' ' )
				{
					return WHITESPACE;
				}
				// ASCII '0' = 48, '9' = 57
				if ( chr < 48 || chr > 57 )
				{
					return MIXED;
				}
			}

			// Catch all
			return INVALID;
		}

		return null;
	}

	/**
	 * Test that the string number representation is a positive integer of any length
	 *
	 * @param number The string representation of an integer number
	 * @return True if this is valid, false otherwise
	 */
	static boolean isValidPositiveIntegerRepresentation( String number )
	{
		// Empty string
		if ( number.isEmpty() )
		{
			return false;
		}

		// Only allow digits 0-9
		// ASCII '0' = 48, '9' = 57
		if ( !number.chars().allMatch( chr -> chr >= 48 && chr <= 57 ) )
		{
			return false;
		}

		// Disallow the first digit to be a zero (hex?)
		return number.charAt( 0 ) != '0';
	}

}
