package server;

public interface Solver<Solution, Problem> {

	public Solution solve(Problem problem);
}