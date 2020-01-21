package test;

import java.util.Random;

import expression.Calculator;

public class MainTrain {

	public static void main(String[] args) {

		// run server for tests
		Random r = new Random();
		int port = 6000 + r.nextInt(1000);
		TestSetter.runServer(port);

		try {
			// test cost matrix problem
			System.out.println("test cost matrix :  ");
			TestCostMatrix.run();

			// test client-server communication
			System.out.println("test client 1 : ");
			TestServer.runClient(port);
			System.out.println("test client 2 : ");
			TestServer.runClient(port);
			System.out.println("test client 3 : ");
			TestServer.runClient(port);
			System.out.println("test client 4 : ");
			TestServer.runClient(port);
			System.out.println("test client 5 : ");
			TestServer.runClient(port);

			// test expression calculator
			System.out.println("test expression calculator : ");
			double num = Calculator.calc("3 * 5 - (8+2)");
			System.out.println("result from expression calculator is = " + num);

			// test interpreter with simulator demo
			System.out.println("test interpreter : ");
			TestInterpreter.run();

		} finally {
			TestSetter.stopServer();
		}
	}
}