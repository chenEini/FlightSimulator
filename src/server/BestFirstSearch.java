package server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class BestFirstSearch<T> extends CommonSearcher<T> {

	@Override
	public List<State<T>> search(Searchable<T> s) {
		openList.clear(); // make sure to clear before start using

		addToOpenList(s.getInitialState());
		HashSet<State<T>> closedSet = new HashSet<State<T>>();

		while (openList.size() > 0) {
			State<T> n = popOpenList(); // dequeue
			closedSet.add(n);

			if (n.equals(s.getGoalState()))
				return backTrace(n, s.getInitialState()); // back traces through the parents

			List<State<T>> successors = s.getAllPossibleStates(n);

			for (State<T> state : successors) {
				if (!closedSet.contains(state) && !openList.contains(state)) {
					state.setCameFrom(n);
					addToOpenList(state);
				} else {
					for (State<T> existState : openList) {
						if (existState.equals(state)) {
							if (state.getCost() < existState.getCost()) {
								openList.remove(existState);
								state.setCameFrom(n);
								addToOpenList(state);
							}
						}
					}
				}
			}
		}
		return null;
	}

	private List<State<T>> backTrace(State<T> goal, State<T> initial) {

		ArrayList<State<T>> solution = new ArrayList<State<T>>();
		State<T> state = goal;

		while (!state.equals(initial)) {
			solution.add(state);
			state = state.getCameFrom();
		}

		solution.add(initial);

		Collections.reverse(solution);

		return solution;
	}
}