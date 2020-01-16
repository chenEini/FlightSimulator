package command;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ConnectCommand implements Command {

	private static Socket simulator = null;
	private static PrintWriter out = null;

	@Override
	public void doCommand(List<String> str) {
		try {
			simulator = new Socket(str.get(0), Integer.parseInt(str.get(1)));
			out = new PrintWriter(simulator.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void simulatorSetCommand(String setCommand) {
		out.println(setCommand);
	}

	public static void disconnect() {
		out.println("bye");
		out.close();

		try {
			simulator.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}