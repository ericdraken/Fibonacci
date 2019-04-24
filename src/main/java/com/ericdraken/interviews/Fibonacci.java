/*
 * Copyright (c) 2019. Eric Draken - ericdraken.com
 */

package com.ericdraken.interviews;

import java.math.BigInteger;

public class Fibonacci
{
	public static BigInteger[] getSequenceAsArray( int numRequired ) throws IllegalArgumentException
	{
		if ( numRequired <= 0 )
		{
			throw new IllegalArgumentException( "The number of Fibonacci numbers requested must be positive" );
		}

		BigInteger[] sequence = new BigInteger[numRequired];

		// Initialize the seed values
		// Note: the F(0) = 0, so the 1st Fib is 0, not 1.
		// REF: http://en.wikipedia.org/wiki/Fibonacci_sequence
		BigInteger a = BigInteger.valueOf( 0 );
		BigInteger b = BigInteger.valueOf( 1 );
		BigInteger sum;

		sequence[0] = a;
		if ( numRequired > 1 )	// Easier than splitting the array later
		{
			sequence[1] = b;

			// A simple O(numRequired) for-loop is needed
			// because each number in the Fibonacci sequence
			// must be printed once, so O(numRequired)
			// is the best we can do computationally;
			// Note: The 1st Fib is 0, so we need to subtract 1
			for ( int i = 2; i < numRequired; i++ )
			{
				// a + b = sum
				sum = a.add( b );
				sequence[i] = sum;

				// Shift a and b forward
				a = b;
				b = sum;
			}
		}

		return sequence;
	}
}
