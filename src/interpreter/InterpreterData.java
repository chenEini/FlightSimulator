package interpreter;

import java.util.HashMap;

import command.ConnectCommand;

public class InterpreterData {

	private HashMap<String, Double> symbolTable;
	private HashMap<String, String> bindTable;
	private HashMap<String, Double> simulatorBindData;

	public InterpreterData() {
		symbolTable = new HashMap<String, Double>();
		bindTable = new HashMap<String, String>();
		simulatorBindData = new HashMap<String, Double>();
	}

	public void addSymbol(String sym, Double val) {
		symbolTable.put(sym, val);
	}

	public void addBind(String sym, String val) {
		bindTable.put(sym, val);
	}
	
	public void addSimulatorBindVal(String sym, Double val) {
		simulatorBindData.put(sym, val);
	}

	public boolean exists(String sym) {
		return (symbolTable.containsKey(sym) || bindTable.containsKey(sym));
	}
	
	public void setValue(String sym, Double val) {
		if (bindTable.containsKey(sym)) {
			ConnectCommand.simulatorSetCommand("set " + bindTable.get(sym) + " " + val);
			simulatorBindData.put(bindTable.get(sym), val);
		} else if (symbolTable.containsKey(sym)) {
			symbolTable.put(sym, val);
		}
	}

	public Double getValue(String sym) {
		if (bindTable.containsKey(sym)) {
			return simulatorBindData.get(bindTable.get(sym));
		} else if (symbolTable.containsKey(sym)) {
			return symbolTable.get(sym);
		}
		return null;
	}
}