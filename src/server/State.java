package server;

public class State<T> implements Comparable<State<T>> {

	private T state;
	private State<T> cameFrom;
	private double cost;

	public State(T state) {
		this.state = state;
		cameFrom = null;
		cost = 0;
	}

	public State(T state, State<T> cameFrom, double cost) {
		this.state = state;
		this.cameFrom = cameFrom;
		this.cost = cost;
	}

	public T getState() {
		return state;
	}

	public void setState(T state) {
		this.state = state;
	}

	public State<T> getCameFrom() {
		return cameFrom;
	}

	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public boolean equals(State<T> s) {
		return this.state.equals(s.getState());
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if (!(this.getClass() == o.getClass()))
			return false;

		return this.state.equals(((State<T>) o).getState());
	}

	@Override
	public int compareTo(State<T> s) {
		return (int) (this.getCost() - s.getCost());
	}

	@Override
	public int hashCode() {
		return this.state.hashCode();
	}

	@Override
	public String toString() {
		return this.state.toString();
	}
}