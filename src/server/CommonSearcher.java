package server;

import java.util.List;
import java.util.PriorityQueue;

public abstract class CommonSearcher<T> implements Searcher<T> {

	protected PriorityQueue<State<T>> openList;
	private int evaluatedNodes;

	public CommonSearcher() {
		openList = new PriorityQueue<State<T>>();
		evaluatedNodes = 0;
	}

	protected void addToOpenList(State<T> s) {
		openList.add(s);
	}

	protected State<T> popOpenList() {
		evaluatedNodes++;
		return openList.poll();
	}

	@Override
	public abstract List<State<T>> search(Searchable<T> s);

	@Override
	public int getNumberOfNodesEvaluated() {
		return evaluatedNodes;
	}
}
