package command;

import interpreter.FlightSimulatorInterpreter;
import java.util.List;

public class BindCommand implements Command {

	@Override
	public void doCommand(List<String> str) {
		FlightSimulatorInterpreter.getData().addBind(str.get(0), str.get(1));
	}
}