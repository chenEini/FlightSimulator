package server;

import java.io.*;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class MyClientHandler implements ClientHandler {
	
	private SearcherSolver<String> solver;

	public MyClientHandler(SearcherSolver<String> solver) {
		this.solver = solver;
	}

	@Override
	public void handleClient(InputStream input, OutputStream output) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(input));
			PrintWriter out = new PrintWriter(output, true);

			readInputsAndSend(in, out, "end");

			in.close();
			out.close();

		} catch (UnknownHostException e) {
		} catch (IOException e) {
		}
	}

	// private methods
	private void readInputsAndSend(BufferedReader in, PrintWriter out, String finishMatrixStr) {
		try {
			String line;
			ArrayList<String> matrix = new ArrayList<String>();

			while (!(line = in.readLine()).equals(finishMatrixStr)) {
				matrix.add(line);
			}

			String initialState = in.readLine();
			String goalState = in.readLine();

			CostMatrix costMatrix = new CostMatrix(matrix, new State<String>(initialState),
					new State<String>(goalState));

			List<State<String>> solution = solver.solve(costMatrix);
			String finalSolution = costMatrix.getReadablePath(solution);
			out.println(finalSolution);

			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}