package interpreter;

public class FlightSimulatorInterpreter {

	private Lexer lexer;
	private Parser parser;
	private static int returnValue = 0;
	private static InterpreterData data = new InterpreterData();

	public FlightSimulatorInterpreter() {
		this.lexer = Lexer.getInstance();
		this.parser = Parser.getInstance();
	}

	public static void setReturnValue(int val) {
		returnValue = val;
	}

	public int interpret(String[] lines) {
		for (String line : lines) {
			parser.parse(lexer.lexer(line));
		}
		return returnValue;
	}

	public static InterpreterData getData() {
		return data;
	}
}