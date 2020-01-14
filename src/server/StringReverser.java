package server;

public class StringReverser implements Solver<String, String> {

	@Override
	public String solve(String problem) {
		return new StringBuilder(problem).reverse().toString();
	}
}