package interpreter;

import java.util.HashMap;

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
}