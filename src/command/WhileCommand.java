package command;

import java.util.ArrayList;
import java.util.List;

import expression.Calculator;

public class WhileCommand implements Command {

	private List<Command> commandLst;
	private List<List<String>> paramsLst;
	private String condition;

	public WhileCommand() {
		super();
		this.commandLst = new ArrayList<Command>();
		this.paramsLst = new ArrayList<List<String>>();
	}

	@Override
	public void doCommand(List<String> str) {

		while (checkCondition()) {
			for (int i = 0; i < commandLst.size(); i++) {
				commandLst.get(i).doCommand(paramsLst.get(i));
			}
		}
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	private boolean checkCondition() {
		String[] split = condition.split(" ");
		Double p1 = Calculator.calc(split[0]);
		Double p2 = Calculator.calc(split[2]);
		boolean condVal;

		switch (split[1]) {
		case "==":
			condVal = p1 == p2;
			break;
		case "<":
			condVal = p1 < p2;
			break;
		case ">":
			condVal = p1 > p2;
			break;
		case "<=":
			condVal = p1 <= p2;
			break;
		case ">=":
			condVal = p1 >= p2;
			break;
		case "!=":
			condVal = p1 != p2;
			break;
		default:
			condVal = false;
		}
		return condVal;
	}

	public void addCommand(Command c) {
		commandLst.add(c);
	}

	public void addParams(List<String> params) {
		paramsLst.add(params);
	}

}