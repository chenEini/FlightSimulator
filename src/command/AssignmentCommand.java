package command;

import java.util.List;

import interpreter.FlightSimulatorInterpreter;
import expression.Calculator;

public class AssignmentCommand implements Command {

	@Override
	public void doCommand(List<String> str) {
		FlightSimulatorInterpreter.getData().setValue(str.get(0), Calculator.calc(str.get(1)));
	}
}