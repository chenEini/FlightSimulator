package command;

import java.util.List;

import interpreter.FlightSimulatorInterpreter;

public class VarCommand implements Command {

	@Override
	public void doCommand(List<String> str) {
		FlightSimulatorInterpreter.getData().addSymbol(str.get(0), null);
	}
}