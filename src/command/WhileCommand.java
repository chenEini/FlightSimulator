package command;

import java.util.List;
import java.util.ArrayList;
import expression.Calculator;

public class WhileCommand implements Command {

	private List<Command> commandList;
	private List<List<String>> paramsList;
	private String condition;

	public WhileCommand() {
		this.commandList = new ArrayList<Command>();
		this.paramsList = new ArrayList<List<String>>();
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public void addCommand(Command c) {
		commandList.add(c);
	}

	public void addParams(List<String> params) {
		paramsList.add(params);
	}

	@Override
	public void doCommand(List<String> str) {
		while (checkCondition()) {
			for (int i = 0; i < commandList.size(); i++) {
				commandList.get(i).doCommand(paramsList.get(i));
			}
		}
	}

	private boolean checkCondition() {
		boolean conditionVal;

		String[] split = condition.split("((?<=<|>|==|!=|<=|>=)|(?=<|>|==|!=|<=|>=))");
		Double p1 = Calculator.calc(split[0]);
		Double p2 = Calculator.calc(split[2]);

		switch (split[1]) {
		case "<":
			conditionVal = p1 < p2;
			break;
		case ">":
			conditionVal = p1 > p2;
			break;
		case "<=":
			conditionVal = p1 <= p2;
			break;
		case ">=":
			conditionVal = p1 >= p2;
			break;
		case "==":
			conditionVal = p1 == p2;
			break;
		case "!=":
			conditionVal = p1 != p2;
			break;
		default:
			conditionVal = false;
		}
		return conditionVal;
	}
}