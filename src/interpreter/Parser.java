package interpreter;

import java.util.*;
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

	private static boolean whileMode = false;
	private WhileCommand command = new WhileCommand();

	private Parser() {
	}

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
		String str = "";
		List<String> lineList = new ArrayList<>(Arrays.asList(line));

		ListIterator<String> it = lineList.listIterator();

		while (it.hasNext()) {

			str = it.next();

			if (commandMap.containsKey(str)) {

				if (whileMode) {
					it.remove();
					command.addCommand(commandMap.get(str));
					command.addParams(lineList);
				}
				else if (str.equals("=") && it.hasNext() && lineList.get(it.nextIndex()).equals("bind")) {
					it.remove(); // remove the symbol "="
					it.next(); // get the symbol "bind"
					it.remove(); // remove the symbol "bind"
					commandMap.get("bind").doCommand(lineList);
				}
				else if (str.equals("while")) {
					it.remove(); // remove the symbol "while"
					whileMode = true;
					command.setCondition(it.next());
				} 
				else {
					it.remove();
					commandMap.get(str).doCommand(lineList);
				}
			} 
			else if (str.equals("}")) {
				whileMode = false;
				command.doCommand(null);
			}
		}
	}
}