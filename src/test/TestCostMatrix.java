package test;

import java.util.ArrayList;
import java.util.PriorityQueue;
import server.CostMatrix;
import server.State;
import server.BestFirstSearch;

public class TestCostMatrix {

	public static void run() {

		State<String> a = new State<String>("A", null, 15);
		State<String> b = new State<String>("B", null, 10);
		State<String> c = new State<String>("A", null, 12133);

		PriorityQueue<State<String>> openList = new PriorityQueue<State<String>>();
		openList.add(a);
		openList.add(b);

		for (State<String> state : openList) {
			System.out.println(state.getState());
		}
		System.out.println(openList.contains(c));

		String row1 = "3,1,5";
		String row2 = "2,1,5";
		String row3 = "3,10,7";

		ArrayList<String> matrix = new ArrayList<String>();

		matrix.add(row1);
		matrix.add(row2);
		matrix.add(row3);

		CostMatrix costMatrix = new CostMatrix(matrix, new State<String>("0,0"), new State<String>("2,2"));

		BestFirstSearch<String> bfs = new BestFirstSearch<String>();

		System.out.println(costMatrix.getReadablePath(bfs.search(costMatrix)));
	}
}