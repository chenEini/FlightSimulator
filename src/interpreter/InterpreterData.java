package interpreter;

import java.util.HashMap;

import server.FlightSimulatorClientHandler;

public class InterpreterData {

	private HashMap<String, Double> symbolTable;
	private HashMap<String, String> bindTable;

	public InterpreterData() {
		symbolTable = new HashMap<String, Double>();
		bindTable = new HashMap<String, String>();
	}

	public void addSymbol(String sym, Double val) {
		symbolTable.put(sym, val);
	}

	public Double getSymbol(String sym) {
		return symbolTable.get(sym); // return null is symbol not exist
	}

	public void addBind(String sym, String val) {
		bindTable.put(sym, val);
	}

	public String getBind(String sym) {
		return bindTable.get(sym); // return null is symbol not exist
	}

	public boolean exists(String sym) {
		return (symbolTable.containsKey(sym) || bindTable.containsKey(sym));
	}

	public void setValue(String sym, Double val) {
		if (bindTable.containsKey(sym)) {
			// send command to client handler : "set" + bindTable.get(sym) + val
		} else if (symbolTable.containsKey(sym)) {
			symbolTable.put(sym, val);
		}
	}

	public Double getValue(String sym) {
		if (bindTable.containsKey(sym)) {
			return FlightSimulatorClientHandler.getUpdatedVal(bindTable.get(sym));
		} else if (symbolTable.containsKey(sym)) {
			return symbolTable.get(sym);
		}
		return null;
	}
}