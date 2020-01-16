package command;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ConnectCommand implements Command {

	private static Socket simulator = null;
	private static ObjectOutputStream out = null;

	@Override
	public void doCommand(List<String> str) {
		try {
			simulator = new Socket(str.get(0), Integer.parseInt(str.get(1)));
			out = new ObjectOutputStream(simulator.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void simulatorSetCommand(String setCommand) {
		try {
			out.writeObject(setCommand);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void disconnect() {
		try {
			out.writeObject("bye");
			out.close();
			simulator.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}