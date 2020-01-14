package interpreter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import command.AssignmentCommand;
import command.BindCommand;
import command.Command;
import command.ConnectCommand;
import command.DisconnectCommand;
import command.OpenServerCommand;
import command.ReturnCommand;
import command.VarCommand;
import command.WhileCommand;

public class Parser {

	private static Parser parserInstance = null;
	private static HashMap<String, Command> commandMap = new HashMap<String, Command>();

	public static Parser getInstance() {
		if (parserInstance == null) {
			parserInstance = new Parser();
			initializeCommandMap();
		}
		return parserInstance;
	}

	private static void initializeCommandMap() {
		commandMap.put("openDataServer", new OpenServerCommand());
		commandMap.put("connect", new ConnectCommand());
		commandMap.put("var", new VarCommand());
		commandMap.put("bind", new BindCommand());
		commandMap.put("=", new AssignmentCommand());
		commandMap.put("return", new ReturnCommand());
		commandMap.put("disconnect", new DisconnectCommand());
		commandMap.put("while", new WhileCommand());
	}

	public void parse(String[] line) {

		List<String> lineList = Arrays.asList(line);

		for (int i = 0; i < lineList.size(); i++) {
			String key = lineList.get(i);

			if (commandMap.containsKey(key)) {
				if (key == "=" && i < lineList.size() - 1 && lineList.get(i + 1) == "bind") {
					commandMap.get(lineList.get(i + 1)).doCommand(Arrays.asList(lineList.get(i + 2)));
					lineList.remove(i + 1);
				}
				lineList.remove(i);
				commandMap.get(key).doCommand(lineList);
			} else {
				// change variable to value
				// if null do nothing
			}
		}
	}
}
