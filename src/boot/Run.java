package boot;

import server.FileCacheManager;
import server.MySerialServer;
import server.MyTestClientHandler;
import server.StringReverser;

public class Run {

	public static void main(String[] args) throws Exception {

		StringReverser solver = new StringReverser();
		FileCacheManager cache = new FileCacheManager();
		MyTestClientHandler handler = new MyTestClientHandler(solver, cache);
		MySerialServer server = new MySerialServer(Integer.parseInt(args[0]), handler);

		server.start();
		Thread.sleep(60 * 1000);
		server.stop();
	}
}