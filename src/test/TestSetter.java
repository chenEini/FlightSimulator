package test;

import server.MySerialServer;
import server.BestFirstSearch;
import server.SearcherSolver;
import server.MyClientHandler;

public class TestSetter {

	static MySerialServer s;

	public static void runServer(int port) {
		// put the code here that runs your server

		BestFirstSearch<String> bfs = new BestFirstSearch<String>();
		SearcherSolver<String> solver = new SearcherSolver<String>(bfs);

		s = new MySerialServer(port, new MyClientHandler(solver));
		s.start();
	}

	public static void stopServer() {
		// put the code here that stops your server

		s.stop();
	}
}