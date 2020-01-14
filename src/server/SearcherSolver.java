package server;

import java.util.List;

public class SearcherSolver<T> implements Solver<List<State<T>>, Searchable<T>> {

	private Searcher<T> searcher;

	public SearcherSolver(Searcher<T> searcher) {
		super();
		this.searcher = searcher;
	}

	@Override
	public List<State<T>> solve(Searchable<T> problem) {
		return searcher.search(problem);
	}
}