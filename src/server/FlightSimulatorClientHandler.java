package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.net.UnknownHostException;

public class FlightSimulatorClientHandler implements ClientHandler {

	private int frequency;
	private String clientInput;
	private static HashMap<String, Double> simulatorData = new HashMap<String, Double>();

	private class MyTimerTask extends TimerTask {
		@Override
		public void run() {
			if (clientInput != null) {
				String[] values = clientInput.split(",");
				simulatorData.put("SimX", Double.parseDouble(values[0]));
				simulatorData.put("SimY", Double.parseDouble(values[1]));
				simulatorData.put("SimZ", Double.parseDouble(values[2]));
			}
		}
	}

	public FlightSimulatorClientHandler(int frequency) {
		this.frequency = frequency;

		// for test only
		simulatorData.put("SimX", 0.0);
		simulatorData.put("SimY", 0.0);
		simulatorData.put("SimZ", 0.0);
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

	public static Double getUpdatedVal(String key) {
		return (simulatorData != null) ? simulatorData.get(key) : null;
	}
}