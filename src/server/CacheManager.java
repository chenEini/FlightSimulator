package server;

public interface CacheManager<Solution, Problem> {

	public boolean solutionExist(Problem problem);

	public Solution getSolution(Problem problem);

	public void saveSolution(Problem problem, Solution solution);
}