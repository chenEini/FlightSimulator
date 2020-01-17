package server;

import java.util.ArrayList;
import java.util.List;

public class CostMatrix implements Searchable<String> {

	int rowSize;
	int colSize;
	Double[][] matrix;

	State<String> initialState;
	State<String> goalState;

	public CostMatrix(List<String> matrix, State<String> initialState, State<String> goalState) {
		this.matrix = convertToDoubleMatrix(matrix);
		this.initialState = initialState;
		this.goalState = goalState;
	}

	private Double[][] convertToDoubleMatrix(List<String> matrix) {
		rowSize = matrix.size();
		colSize = matrix.get(0).split(",").length;

		Double[][] mat = new Double[rowSize][colSize];

		int rowNum = 0;

		for (String s : matrix) {
			int colNum = 0;
			String[] row = s.split(",");

			for (String num : row) {
				mat[rowNum][colNum] = Double.parseDouble(num);
				colNum++;
			}
			rowNum++;
		}

		return mat;
	}

	@Override
	public State<String> getInitialState() {
		return initialState;
	}

	@Override
	public State<String> getGoalState() {
		return goalState;
	}

	@Override
	public List<State<String>> getAllPossibleStates(State<String> s) {

		List<State<String>> successorsList = new ArrayList<State<String>>();

		int stateRow = Integer.parseInt(s.getState().split(",")[0]);
		int stateCol = Integer.parseInt(s.getState().split(",")[1]);
		double stateCost = s.getCost();

		String position;

		// check if we can go up
		if (stateRow > 0) {
			position = (stateRow - 1) + "," + stateCol;
			double currentStateCost = this.matrix[stateRow - 1][stateCol];
			successorsList.add(new State<String>(position, s, stateCost + currentStateCost));
		}

		// check if we can go down
		if (stateRow < rowSize - 1) {
			position = (stateRow + 1) + "," + stateCol;
			double currentStateCost = this.matrix[stateRow + 1][stateCol];
			successorsList.add(new State<String>(position, s, stateCost + currentStateCost));
		}

		// check if we can go right
		if (stateCol < colSize - 1) {
			position = stateRow + "," + (stateCol + 1);
			double currentStateCost = this.matrix[stateRow][stateCol + 1];
			successorsList.add(new State<String>(position, s, stateCost + currentStateCost));
		}

		// check if we can go left
		if (stateCol > 0) {
			position = stateRow + "," + (stateCol - 1);
			double currentStateCost = this.matrix[stateRow][stateCol - 1];
			successorsList.add(new State<String>(position, s, stateCost + currentStateCost));
		}

		return successorsList;
	}

	public String getReadablePath(List<State<String>> path) {
		String readablePath = "";

		for (int i = 0; i < path.size() - 1; i++) {

			State<String> currentState = path.get(i);
			State<String> nextState = path.get(i + 1);

			int currentStateRow = Integer.parseInt(currentState.getState().split(",")[0]);
			int currentStateCol = Integer.parseInt(currentState.getState().split(",")[1]);

			int nextStateRow = Integer.parseInt(nextState.getState().split(",")[0]);
			int nextStateCol = Integer.parseInt(nextState.getState().split(",")[1]);

			if (currentStateRow == nextStateRow + 1 && currentStateCol == nextStateCol) {
				readablePath += "Up,";
			}
			if (currentStateRow == nextStateRow - 1 && currentStateCol == nextStateCol) {
				readablePath += "Down,";
			}
			if (currentStateRow == nextStateRow && currentStateCol == nextStateCol + 1) {
				readablePath += "Left,";
			}
			if (currentStateRow == nextStateRow && currentStateCol == nextStateCol - 1) {
				readablePath += "Right,";
			}
		}

		return readablePath.substring(0, readablePath.length() - 1);
	}
}