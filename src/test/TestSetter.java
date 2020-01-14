package test;

import server.MySerialServer;
import server.BestFirstSearch;
import server.SearcherSolver;
import server.MyClientHandler;

public class TestSetter {

	static MySerialServer s;

	public static void runServer(int port) {
		// run the server
		BestFirstSearch<String> bfs = new BestFirstSearch<String>();
		SearcherSolver<String> solver = new SearcherSolver<String>(bfs);

		s = new MySerialServer(port, new MyClientHandler(solver));
		s.start();
	}

	public static void stopServer() {
		// stop the server
		s.stop();
	}
}