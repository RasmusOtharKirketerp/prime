package prime;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Prime {

	// Time for solving for all(to 1000) Prims is : 424297816
	// Time for solving for all(to 1000) Prims is : 1665677168
	// Time for solving for all(to 1000) Prims is : 1594345161
	// Time for solving for all(to 1000) Prims is : 1620644046
	// Time for solving for all(to 1000) Prims is : 638214825
	// Time for solving for all(to 1000) Prims is : 386908356
	// Time for solving for all(to 1000) Prims is : 344048864
	// Time for solving for all(to 1000) Prims is : 1565715410
	// Time for solving for all(to 1000) Prims is : 7373833
	// Time for solving for all(to 1000) Prims is : 5891955
	// Time for solving for all(to 1000) Prims is : 1573984454
	// Time for solving for all(to 1000) Prims is : 7246623

	// 1 mio.
	// Time for solving for all(to 1000000) Prims is : 278219773803
	// Time for solving for all(to 1000000) Prims is : 17026953503
	// Time for solving for all(to 1000000) Prims is : 14629429831
	// Time for solving for all(to 1000000) Prims is : 4621312491
	// Time for solving for all(to 1000000) Prims is : 4632645240
	// Time for solving for all(to 1000000) Prims is : 4639716456
	// Time for solving for all(to 1000000) Prims is : 691024349

	// 10 mio
	// Time for solving for all(to 10000000) Prims is : 17155761111
	// Time for solving for all(to 10000000) Prims is : 16169135146
	public static boolean traceTime = false;
	public static boolean traceDisplay = false;

	public static ArrayList<Long> foundPrimes = new ArrayList<Long>();
	public static ArrayList<Long> foundPrimes2 = new ArrayList<Long>();
	public static ArrayList<Long> traceNanno2 = new ArrayList<Long>();

	static long findMaxPrime = 1000000;

	public static boolean isPrime(long solveForThisPrime) throws InterruptedException {
		long now = System.nanoTime();
		if (traceDisplay)
			System.out.println("Solving for this prime : " + solveForThisPrime);

		boolean thisIsPrime = false;
		boolean thisHasFactors = false;

		long cd = (int) Math.sqrt(solveForThisPrime);

		for (long counter = cd; counter > 1; counter--) {
			thisIsPrime = true;
			thisHasFactors = false;
			// if any on those modulus calculations is zero then there is a
			// factor, and by definition this is
			// not a prime number.

			// 2 3 5 7 11 13 17 19 23 29
			long res = Math.floorMod(solveForThisPrime, counter);
			if (res == 0) {
				thisHasFactors = true;
				// stop loop
				counter = 0;
			}

		}

		thisIsPrime = !thisHasFactors;

		if (traceTime) {
			long endTime = System.nanoTime();
			System.out.println();
			System.out.println("Time for solving this Prims (" + solveForThisPrime + ") is : " + (endTime - now));
		}
		if (thisIsPrime)
			foundPrimes.add(solveForThisPrime);
		return thisIsPrime;
	}

	public static void printStats(ArrayList<Long> arrPrimes, ArrayList<Long> arrTime) throws FileNotFoundException {
		PrintWriter out = new PrintWriter("prime_Stats2.txt");
		long min = 999999999;
		long max = 0;
		long value = 0;
		long sum = 0;
		for (int i = 0; i < arrTime.size(); i++) {
			sum += arrTime.get(i);
			value = arrTime.get(i);

			if (value > max)
				max = value;

			if (value < min)
				min = value;

			System.out.print(value);
			System.out.println(" Prime : " + arrPrimes.get(i));
			if (i > 0)
			{
				System.out.println("Running avg : " + sum / i);
				out.println(i+";"+ arrPrimes.get(i) +";"      + sum/i);
			}
		}
		out.close();
		System.out.println("Fastest calc was : " + min);
		System.out.println("Slowest calc was : " + max);
		System.out.println("Avg cals was     : " + sum / arrTime.size());

	}

	public static boolean isPrime2(long solveForThisPrime) throws InterruptedException {
		long now = System.nanoTime();
		if (traceDisplay)
			System.out.println("Version II : Solving for this prime : " + solveForThisPrime);

		boolean thisIsPrime = false;
		boolean thisHasFactors = false;

		long cd = (int) Math.sqrt(solveForThisPrime);

		for (int counter = 0; counter < cd; counter++) {
			thisIsPrime = true;
			thisHasFactors = false;
			// if any on those modulus calculations is zero then there is a
			// factor, and by definition this is
			// not a prime number.

			// 2 3 5 7 11 13 17 19 23 29
			if (foundPrimes2.size() > 0) {
				long res = Math.floorMod(solveForThisPrime, foundPrimes2.get(counter));
				if (res == 0) {
					thisHasFactors = true;
					// stop loop
					counter = (int) cd;
				}
			}

		}
		long endTime = System.nanoTime();
		thisIsPrime = !thisHasFactors;

		// System.out.println();
		// System.out.println("Time for solving this Prims (" +
		// solveForThisPrime + ") is : " + (endTime - now));
		if (thisIsPrime) {
			foundPrimes2.add(solveForThisPrime);
			traceNanno2.add((endTime - now));
		}
		return thisIsPrime;
	}

	public static void main(String[] args) throws InterruptedException {
		long now = System.nanoTime();
		if (traceTime) {

			System.out.println(now);

		}

		for (int i = 2; i <= findMaxPrime; i++) {
			// isPrime(i);
			isPrime2(i);
		}
		long endTime = System.nanoTime();
		System.out.println("Time for solving for all(to " + findMaxPrime + ") Prims is : " + (endTime - now));
		try {
			printStats(foundPrimes2, traceNanno2);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
