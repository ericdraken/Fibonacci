/*
 * Copyright (c) 2019. Eric Draken - ericdraken.com
 */

package com.ericdraken.interviews;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.System.getProperty;

public class FibonacciREPL
{
	private final static String VERSION = "1.0";

	/**
	 * Enter the REPL loop
	 * @param args Args are ignored
	 */
	public static void main( String[] args )
	{
		System.out.println( welcomeMessage() );
		System.out.println( help() );

		Scanner in = new Scanner( System.in );
		while ( in.hasNextLine() )
		{
			String str = in.nextLine();    // Get the whole line

			// Be nice and at least trim the input
			str = str.trim();

			// Exit the REPL loop
			if ( str.matches( "^(q|quit|x|exit)" ) )
			{
				break;
			}

			// Show the help message
			if ( str.matches( "^(h|help)" ) )
			{
				System.out.println( help() );
				continue;
			}

			// Display a hint, or the desired Fibonacci sequence
			String hint = ValidatePositive.validateWithHints( str );
			if ( hint != null )
			{
				System.err.println( String.format( "%s %s", hint, instructions() ) );
			}
			else
			{
				// Get the sequence
				BigInteger[] sequence = Fibonacci.getSequenceAsArray( Integer.valueOf( str ) );

				System.out.println();
				System.out.println(
					Arrays
						.stream( sequence )
						.map( BigInteger::toString )    // Map big integers to strings
						.collect(
							Collectors.joining( " ", "", "" )		// Join them on spaces
						)
				);
				System.out.println();
			}
		}
	}

	/**
	 * Return the detailed help for the REPL
	 *
	 * @return The instructions
	 */
	private static String help()
	{
		return
			String.join(
				System.lineSeparator(),
				Arrays.asList(
					"Quit by entering 'q' or 'quit'. See this message again with 'h' or 'help'.",
					"Please enter the desired number of Fibonacci numbers:"
				)
			);
	}

	/**
	 * Return the instructions for the REPL
	 *
	 * @return The instructions
	 */
	private static String instructions()
	{
		return "Please enter the desired number of Fibonacci numbers (q to quit):";
	}

	/**
	 * Return a simple welcome message and Java version
	 *
	 * @return Welcome message
	 */
	private static String welcomeMessage()
	{
		String str = String.format(
			"Welcome to Fibonacci Sequence version %s (Java %s)",
			VERSION,
			getProperty( "java.version" )
		);
		String lines = String.format( "%" + str.length() + "s", "" ).replace( ' ', '-' );
		return lines + System.lineSeparator() + str + System.lineSeparator() + lines;
	}
}
