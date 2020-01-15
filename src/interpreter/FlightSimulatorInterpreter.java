package interpreter;

public class FlightSimulatorInterpreter {

	private Lexer lexer;
	private Parser parser;
	private int returnValue;
	private static InterpreterData data = new InterpreterData();

	public FlightSimulatorInterpreter(Lexer lexer, Parser parser) {
		super();
		this.lexer = lexer;
		this.parser = parser;
		this.returnValue = 0;
	}

	public FlightSimulatorInterpreter() {
		super();
		this.lexer = new Lexer();
		this.parser = new Parser();
		this.returnValue = 0;
	}

	public void setReturnValue(int val) {
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