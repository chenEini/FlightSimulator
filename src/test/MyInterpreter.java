package test;

import interpreter.FlightSimulatorInterpreter;

public class MyInterpreter {

	public static int interpret(String[] lines) {

		FlightSimulatorInterpreter interpreter = new FlightSimulatorInterpreter();
		return interpreter.interpret(lines);
	}
}