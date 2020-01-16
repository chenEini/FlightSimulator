package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;
import java.net.UnknownHostException;

import interpreter.FlightSimulatorInterpreter;

public class FlightSimulatorClientHandler implements ClientHandler {

	private int frequency;
	private String clientInput;

	private class MyTimerTask extends TimerTask {
		@Override
		public void run() {
			if (clientInput != null) {
				String[] values = clientInput.split(",");
				FlightSimulatorInterpreter.getData().addSimulatorBindVal("simX", Double.parseDouble(values[0]));
				FlightSimulatorInterpreter.getData().addSimulatorBindVal("simY", Double.parseDouble(values[1]));
				FlightSimulatorInterpreter.getData().addSimulatorBindVal("simZ", Double.parseDouble(values[2]));
			}
		}
	}

	public FlightSimulatorClientHandler(int frequency) {
		this.frequency = frequency;

		// for test only
		FlightSimulatorInterpreter.getData().addSimulatorBindVal("simX", 0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindVal("simY", 0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindVal("simZ", 0.0);
	}

	@Override
	public void handleClient(InputStream input, OutputStream output) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(input));

			Timer timer = new Timer();
			timer.scheduleAtFixedRate(new MyTimerTask(), 0, 1000 / frequency);

			while ((clientInput = in.readLine()) != null)

				timer.cancel();
			in.close();

		} catch (UnknownHostException e) {
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}