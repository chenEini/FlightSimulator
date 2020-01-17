package command;

import java.util.List;
import server.ClientHandler;
import server.MySerialServer;
import server.FlightSimulatorClientHandler;

public class OpenServerCommand implements Command {
	
	private static MySerialServer server;

	@Override
	public void doCommand(List<String> str) {
		int port = Integer.parseInt(str.get(0));
		int frequency = Integer.parseInt(str.get(1));

		ClientHandler handler = new FlightSimulatorClientHandler(frequency);

		server = new MySerialServer(port, handler);
		server.start();
	}

	public static void closeServer() {
		if (server != null)
			server.stop();
	}
}