package command;

import java.util.List;

import server.ClientHandler;
import server.FlightSimulatorClientHandler;
import server.MySerialServer;

public class OpenServerCommand implements Command {

	static MySerialServer server;

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