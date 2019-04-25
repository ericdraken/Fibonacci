/*
 * Copyright (c) 2019. Eric Draken - ericdraken.com
 */

package com.ericdraken.interviews;

import java.math.BigInteger;
import java.util.Iterator;

/**
 * This is a very simple iterator that returns the next
 * Fibonacci number in the sequence starting with 0. That is,
 * when next() is called the first time, it will return 0, then 1,
 * and so forth. Note that hasNext() will always return true
 * as the sequence is unbounded.
 */
public class FibonacciIterator implements Iterator<BigInteger>
{
	// Initialize the seed values
	// Note: the F(0) = 0, so the 1st Fib is 0, not 1.
	// REF: http://en.wikipedia.org/wiki/Fibonacci_sequence
	private BigInteger a = null;
	private BigInteger b = null;

	/**
	 * Returns the next number in the Fibonacci sequence.
	 *
	 * @return The next number
	 */
	@Override
	public BigInteger next()
	{
		// Seed and return a
		if ( a == null )
		{
			a = BigInteger.valueOf( 0 );
			return a;
		}

		// Seed and return b
		if ( b == null )
		{
			b = BigInteger.valueOf( 1 );
			return b;
		}

		// a + b = sum
		BigInteger sum = a.add( b );

		// Shift a and b forward
		a = b;
		b = sum;

		return sum;
	}

	/**
	 * This will always return true for the unbounded Fibonacci sequence
	 *
	 * @return True, always
	 */
	@Override
	public boolean hasNext()
	{
		return true;
	}

	/**
	 * This is not supported
	 */
	@Override
	public void remove()
	{
		throw new UnsupportedOperationException( "Removal of Fibonacci numbers generated on the fly is not supported" );
	}
}
