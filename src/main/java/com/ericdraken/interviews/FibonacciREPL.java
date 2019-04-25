/*
 * Copyright (c) 2019. Eric Draken - ericdraken.com
 */

package com.ericdraken.interviews;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.getProperty;

public class FibonacciREPL
{
	private final static String VERSION = "1.0";

	// Past this number there is a noticeable lag in displaying numbers.
	private final static int MAX_SEQUENCE_DISPLAY_LENGTH = 3_000;

	// Past this number writing Fibs to disk becomes problematic.
	// e.g. Writing 10,000 fibs to disk will occupy about 10MB of text.
	// e.g. Writing 100,000 fibs will occupy about 1GB of text disk space!
	private final static int MAX_SEQUENCE_CALCULATION_LENGTH = 10_000;

	// Template for the output file on large Fib sequences
	private final static String OUTPUT_FILE_TEMPLATE = "fibs-%s.txt";

	// Faster processing than with System.out to avoid unnecessary UTF processing.
	// REF: https://www.rgagnon.com/javadetails/java-0603.html
	private static final OutputStreamWriter out =
		new OutputStreamWriter(
			new FileOutputStream( FileDescriptor.out ), StandardCharsets.US_ASCII );

	/**
	 * Enter the REPL loop
	 *
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
				continue;
			}

			// Check an upper limit on the number of Fibonacci numbers we can calculate.
			// Use the length of the string representation of this number to check.
			// This is to prevent a huge number from crashing the Integer.parseInt() routine.
			if ( str.length() > String.valueOf( MAX_SEQUENCE_CALCULATION_LENGTH ).length() )
			{
				System.err.println( maxLengthWarning() );
				continue;
			}

			// Safely get the number of Fibs desired
			int length = Integer.parseInt( str, 10 );
			if ( length > MAX_SEQUENCE_CALCULATION_LENGTH )
			{
				System.err.println( maxLengthWarning() );
				continue;
			}

			try
			{
				// Display the Fibs, or write them to disk
				if ( length <= MAX_SEQUENCE_DISPLAY_LENGTH )
				{
					writeFibSequence( length, out );
					out.flush();
					System.out.println();
				}
				else
				{
					// Write the file to the system PWD
					String filename = String.format( OUTPUT_FILE_TEMPLATE, length );
					try (
						OutputStreamWriter writer = new OutputStreamWriter(
							new FileOutputStream( new File( filename ) ) )
					)
					{
						writeFibSequence( length, writer );
						System.out.println(
							String.format( "Wrote the first %d Fibonacci numbers to %s.", length, filename )
						);
					}
				}

				// Continue
				System.out.println();
				System.out.println( instructions() );
			}
			catch ( IOException e )
			{
				System.err.println( "Unable to write the Fibonacci sequence. Reason: " + e.getMessage() );
				System.out.println( instructions() );
			}
		}
	}

	/**
	 * Write the Fibonacci sequence up to length [length] to a Writer
	 * which can either be a file or the system console.
	 *
	 * @param length The number of Fibonacci numbers to write
	 * @param out The output Writer
	 * @throws IOException Exception if the Writer is closed or invalid
	 */
	private static void writeFibSequence( int length, Writer out ) throws IOException
	{
		FibonacciIterator it = new FibonacciIterator();

		// A simple Omega(length) for-loop is needed because each number in the Fibonacci sequence
		// must be printed once, so Omega(length) is the lowest bound we can do computationally.
		// Note: This is not an O(length) runtime because the string length to print grows non-linearly.
		for ( long i = 1; i <= length; i++ )
		{
			out.write( it.next().toString() );
			if ( i < length )
			{
				out.write( ' ' );
			}
		}
		out.flush();
	}

	/**
	 * Return a warning about the max length this
	 * REPL system supports
	 *
	 * @return Warning string
	 */
	private static String maxLengthWarning()
	{
		return String.format(
			"%s %s. %s",
			"The max sequence length is",
			MAX_SEQUENCE_CALCULATION_LENGTH,
			instructions()
		);
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
					"The maximum *displayable* number of Fibonacci numbers is " + MAX_SEQUENCE_DISPLAY_LENGTH + ".",
					"Past this, the sequence will be written to disk up to " + MAX_SEQUENCE_CALCULATION_LENGTH + " numbers.",
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
