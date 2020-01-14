package server;

import java.util.List;

public interface Searchable<T> {

	State<T> getInitialState();

	State<T> getGoalState();

	List<State<T>> getAllPossibleStates(State<T> s);
}