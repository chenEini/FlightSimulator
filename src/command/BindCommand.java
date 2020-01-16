package command;

import java.util.List;

import interpreter.FlightSimulatorInterpreter;

public class BindCommand implements Command {

	@Override
	public void doCommand(List<String> str) {
		FlightSimulatorInterpreter.getData().addBind(str.get(0), str.get(1));
	}
}