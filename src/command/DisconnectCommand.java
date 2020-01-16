package command;

import java.util.List;

public class DisconnectCommand implements Command {

	@Override
	public void doCommand(List<String> str) {
		ConnectCommand.disconnect();
		OpenServerCommand.closeServer();
	}
}