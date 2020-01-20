package interpreter;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Lexer {

	private static Lexer lexerInstance = null;

	private Lexer() {
	}
	
	public static Lexer getInstance() {
		if (lexerInstance == null)
			lexerInstance = new Lexer();
		return lexerInstance;
	}

	public String[] lexer(String line) {
		Scanner sc;
		String str1, str2;
		StringBuilder builder = new StringBuilder();
		String[] tokens = line.split("(?<=([={}])|(bind))|(?=([={}]))");

		for (String token : tokens) {
			sc = new Scanner(token);
			str1 = sc.next();
			builder.append(str1);

			while (sc.hasNext()) {
				str2 = sc.next();
				if (isFullExpression(str1, str2))
					builder.append(",");
				builder.append(str2);
				str1 = str2;
			}
			builder.append(",");
		}
		return builder.toString().split(",");
	}

	private boolean isFullExpression(String str1, String str2) {
		Pattern end = Pattern.compile(".*[\\w)\"]");
		Pattern start = Pattern.compile("[\\w(\"].*");
		return (end.matcher(str1).matches() && start.matcher(str2).matches());
	}
}