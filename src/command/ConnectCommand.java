package command;

import java.util.List;
import java.net.Socket;
import java.io.PrintWriter;

public class ConnectCommand implements Command {

	private static Socket simulator = null;
	private static PrintWriter out = null;

	@Override
	public void doCommand(List<String> str) {
		try {
			Thread.sleep(1350); // waiting for the server to be ready, just for testing

			simulator = new Socket(str.get(0), Integer.parseInt(str.get(1)));
			out = new PrintWriter(simulator.getOutputStream(), true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void simulatorSetCommand(String setCommand) {
		out.println(setCommand);
	}

	public static void disconnect() {
		try {
			Thread.sleep(1000); // waiting for the server to be ready, just for testing

			out.println("bye");

			out.close();
			simulator.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}