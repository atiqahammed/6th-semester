package Calculator;

public class ScientificCalculator {

	public int add(int x, int y) {
		return x + y;
	}

	public double add(double x, int y) {
		return x + y;
	}

	public double add(int x, double y) {
		return x + y;
	}

	public double add(double x, double y) {
		return x + y;
	}

	public int sub(int x, int y) {
		return x - y;
	}

	public double sub(double x, int y) {
		return x - y;
	}

	public double sub(int x, double y) {
		return x - y;
	}

	public double sub(double x, double y) {
		return x - y;
	}

	public int mul(int x, int y) {
		return x * y;
	}

	public int div(int x, int y) {
		return x / y;
	}

	public double log(double x) {
		return Math.log(x);
	}

	public static int log2(int n) {
		if (n <= 0)
			throw new IllegalArgumentException();
		return 31 - Integer.numberOfLeadingZeros(n);
	}

	public int log(int x, int base) {
		return (int) (Math.log(x) / Math.log(base));
	}
	
	public static long lcm(long a, long b)
	{
	    return a * (b / gcd(a, b));
	}

	public static long lcm(long[] input)
	{
	    long result = input[0];
	    for(int i = 1; i < input.length; i++) result = lcm(result, input[i]);
	    return result;
	}
	
	public static int gcd(long x, long y) {
	    return (int) ((y == 0) ? x : gcd(y, x % y));
	}
	
	public double redian(int x) {
		return Math.toRadians(x);
	}
	
	public int sqr(int x) {
		return x*x;
	}
	
	public int cube(int x) {
		return x*x*x;
	}

}
