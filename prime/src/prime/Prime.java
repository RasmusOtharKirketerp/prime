package prime;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Prime {
	// Private
	private boolean traceTime = false;
	private boolean traceDisplay = false;

	private ArrayList<Long> foundPrimes2 = new ArrayList<>();
	private ArrayList<Long> traceNanno2 = new ArrayList<>();

	private long findMaxPrime;

	// Getter and setters....
	public boolean isTraceTime() {
		return traceTime;
	}

	public void setTraceTime(boolean traceTime) {
		this.traceTime = traceTime;
	}

	public boolean isTraceDisplay() {
		return traceDisplay;
	}

	public void setTraceDisplay(boolean traceDisplay) {
		this.traceDisplay = traceDisplay;
	}

	public long getFindMaxPrime() {
		return findMaxPrime;
	}

	public void setFindMaxPrime(long findMaxPrime) {
		this.findMaxPrime = findMaxPrime;
	}

	public Prime(int maxTry) {
		this.setFindMaxPrime(maxTry);
	}

	public void doPrimes(boolean timetrace, boolean displaytrace) throws InterruptedException {
		setTraceTime(timetrace);
		setTraceDisplay(displaytrace);
		long now = System.nanoTime();
		if (isTraceDisplay()) {

			System.out.println(now);

		}

		for (int i = 2; i <= getFindMaxPrime(); i++) {
			isPrime2(i);
		}
		long endTime = System.nanoTime();
		if (traceTime)
			System.out.println("Time for solving for all(to " + getFindMaxPrime() + ") Prims is : " + (endTime - now));
	}

	public void printStats() throws FileNotFoundException {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		PrintWriter out = new PrintWriter("prime_Stats " + findMaxPrime + " " + timeStamp + ".csv");
		out.println("Counter" + ";" + "Prime" + ";" + "avg nanno");
		long min = 999999999;
		long max = 0;
		long value = 0;
		long sum = 0;

		for (int i = 0; i < traceNanno2.size(); i++) {
			sum += traceNanno2.get(i);
			value = traceNanno2.get(i);

			if (value > max)
				max = value;

			if (value < min)
				min = value;

			if (traceDisplay)
				System.out.println(" Prime : " + foundPrimes2.get(i));
			if (i > 0 && traceTime) {
				System.out.print(value);
				System.out.println("Running avg : " + sum / i);

			}
			if (i > 0)
				out.println(i + ";" + foundPrimes2.get(i) + ";" + sum / i);
		}
		
		if (traceTime) {
			System.out.println("Fastest calc was : " + min);
			System.out.println("Slowest calc was : " + max);
			System.out.println("Avg cals was     : " + sum / traceNanno2.size());
			out.println("Fastest calc was : " + min);
			out.println("Slowest calc was : " + max);
			out.println("Avg cals was     : " + sum / traceNanno2.size());
		}
		out.close();

	}

	public boolean hasFactor(long solveForThisPrime, int counter, long sqrtInt) {
		// if any on those modulus calculations is zero then there is a
		// factor, and by definition this is
		// not a prime number.

		// 2 3 5 7 11 13 17 19 23 29

		boolean thisHasFactors = false;
		if (foundPrimes2.size() > 0) {
			long res = Math.floorMod(solveForThisPrime, foundPrimes2.get(counter));
			if (res == 0) {
				thisHasFactors = true;
			}
		}

		return thisHasFactors;

	}

	public boolean isPrime2(long solveForThisPrime) throws InterruptedException {
		long now = System.nanoTime();
		if (isTraceDisplay())
			System.out.println("Version II : Solving for this prime : " + solveForThisPrime);

		boolean thisIsPrime = false;
		boolean thisHasFactors = false;

		long cd = (int) Math.sqrt(solveForThisPrime);

		for (int counter = 0; counter < cd; counter++) {
			thisIsPrime = true;
			thisHasFactors = false;

			if (hasFactor(solveForThisPrime, counter, cd)) {
				counter = (int) cd;
				thisHasFactors = true;
			}
		}

		long endTime = System.nanoTime();

		thisIsPrime = !thisHasFactors;

		if (thisIsPrime) {
			foundPrimes2.add(solveForThisPrime);
			traceNanno2.add((endTime - now));
		}

		return thisIsPrime;
	}

}
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
