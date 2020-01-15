package command;

import interpreter.FlightSimulatorInterpreter;
import java.util.List;

import expression.Calculator;

public class ReturnCommand implements Command {

	@Override
	public void doCommand(List<String> str) {
		StringBuilder expression = new StringBuilder();

		for (String s : str) {
			expression.append(s);
		}

		int result = (int) Calculator.calc(expression.toString());
		FlightSimulatorInterpreter.setReturnValue(result);
	}
}