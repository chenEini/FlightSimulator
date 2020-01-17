package server;

import java.io.*;
import java.net.UnknownHostException;

public class MyTestClientHandler implements ClientHandler {

	private Solver<String, String> solver;
	private CacheManager<String, String> cache;

	public MyTestClientHandler(Solver<String, String> solver, CacheManager<String, String> cache) {
		this.solver = solver;
		this.cache = cache;
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
	private void readInputsAndSend(BufferedReader in, PrintWriter out, String exitStr) {
		try {
			String line;
			while (!(line = in.readLine()).equals(exitStr)) {
				if (cache.solutionExist(line))
					out.println(cache.getSolution(line));
				else {
					String solution = solver.solve(line);
					cache.saveSolution(line, solution);
					out.println(solution);
				}
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}