# Fibonacci Sequence Generator

## Requirements

* Java 8+
* Apache Maven 3.8+
* JUnit 5+ (for parametric testing)

## Running

The easiest way to run Fibonacci is with the precompiled JAR. Make sure the Java executable is on the class path, then navigate to the `lib/` folder. On Linux, run:

    java -jar Fibonacci.jar
    
## REPL operation

When started, the console will prompt you to enter a positive integer:

    ------------------------------------------------------
    Welcome to Fibonacci Sequence version 1.0 (Java 9.0.1)
    ------------------------------------------------------
    Quit by entering 'q' or 'quit'. See this message again with 'h' or 'help'.
    The maximum *displayable* number of Fibonacci numbers is 3000.
    Past this, the sequence will be written to disk up to 10000 numbers.
    Please enter the desired number of Fibonacci numbers:
    
Enter a positive integer up to 3,000 and the Fibonacci sequence will display in the console. Between 3,001 and 10,000 the Fibonacci sequence will be written to a `*.txt` file in the current directory.

    10
    0 1 1 2 3 5 8 13 21 34
    
    5000
    Wrote the first 5000 Fibonacci numbers to fibs-5000.txt.

Valid characters are `0-9` only, and the first character may not be `0`.

### Maximum sequence number
    
The maximum supported sequence length is 10,000 numbers. This is because writing this many Fibs to disk occupies 10MB of text. While the `FibonacciIterator` used in the REPL uses `BigNumber` internally and can handle very large numbers, writing 100,000 Fibs to disk will take up about 1GB of disk space. That is why the arbitrary limit of 10,000 Fibs is imposed.

### Help

Enter 'h' or 'help' at any time to see the help message.

### Quit

Enter 'q' or 'quit' (or 'x' or 'exit') to end the REPL session

## Hint System

If the integer entered is invalid, a hint about why will be presented. For example:

    0
    Positive integers start from 1 Please enter the desired number of Fibonacci numbers (q to quit):
    
    abc
    Only the numerals 0-9 are valid. Please enter the desired number of Fibonacci numbers (q to quit):

## Analysis

A runtime of Ω(length) is needed because each number in the Fibonacci sequence must be printed once, so Ω(length) is the lowest bound we can achieve computationally. 

Note that this is not an O(length) runtime because the printed string length is not constant and grows non-linearly.

---

## Assumptions

Here are the assumptions I used to build the Fibonacci Sequence program:

1. The first Fibonacci number is 0 since F(0) = 0.
2. Only non-negative Fibonacci numbers are displayed. e.g. 0 1 1 2 3 etc.
3. The Fibs are to be displayed immediately, not stored in memory.
4. Include REPL to make testing more fun.
5. Create a utility class that can be used outside REPL i.e. `FibonacciIterator`.
6. Include a standalone jar (see `lib/` folder)
7. Include parametric unit tests (see `src/test/`)
8. Support a sequence of 100,000 or more Fibs in the core `FibonacciIterator`.
9. Handle any error conditions gracefully.
10. Print to `System.out` under 3,000 Fibs.
10. Write to a text file between 3,001 and 10,000 Fibs.

## Getting help

Looking for help? Contact Eric on [LinkedIn](https://www.linkedin.com/in/ericdraken).