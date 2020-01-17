package command;

import java.util.List;
import expression.Calculator;
import interpreter.FlightSimulatorInterpreter;

public class ReturnCommand implements Command {

	@Override
	public void doCommand(List<String> str) {
		int result = (int) Calculator.calc(str.get(0));
		FlightSimulatorInterpreter.setReturnValue(result);
	}
}