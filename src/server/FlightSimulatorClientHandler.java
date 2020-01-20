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

				FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/airspeed-indicator/indicated-speed-kt",Double.parseDouble(values[0]));
				FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/altimeter/indicated-altitude-ft",Double.parseDouble(values[1]));
				FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/altimeter/pressure-alt-ft",Double.parseDouble(values[2]));
				FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/attitude-indicator/indicated-pitch-deg",Double.parseDouble(values[3]));
				FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/attitude-indicator/indicated-roll-deg",Double.parseDouble(values[4]));
				FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/attitude-indicator/internal-pitch-deg",Double.parseDouble(values[5]));
				FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/attitude-indicator/internal-roll-deg",Double.parseDouble(values[6]));
				FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/encoder/indicated-altitude-ft",Double.parseDouble(values[7]));
				FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/encoder/pressure-alt-ft",Double.parseDouble(values[8]));
				FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/gps/indicated-altitude-ft",Double.parseDouble(values[9]));
				FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/gps/indicated-ground-speed-kt",Double.parseDouble(values[10]));
				FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/gps/indicated-vertical-speed",Double.parseDouble(values[11]));
				FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/heading-indicator/indicated-heading-deg", Double.parseDouble(values[12]));
				FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/magnetic-compass/indicated-heading-deg",Double.parseDouble(values[13]));
				FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/slip-skid-ball/indicated-slip-skid",Double.parseDouble(values[14]));
				FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/turn-indicator/indicated-turn-rate",Double.parseDouble(values[15]));
				FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/vertical-speed-indicator/indicated-speed-fpm",Double.parseDouble(values[16]));
				FlightSimulatorInterpreter.getData().addSimulatorBindVal("/controls/flight/aileron", Double.parseDouble(values[17]));
				FlightSimulatorInterpreter.getData().addSimulatorBindVal("/controls/flight/elevator", Double.parseDouble(values[18]));
				FlightSimulatorInterpreter.getData().addSimulatorBindVal("/controls/flight/rudder", Double.parseDouble(values[19]));
				FlightSimulatorInterpreter.getData().addSimulatorBindVal("/controls/flight/flaps", Double.parseDouble(values[20]));
				FlightSimulatorInterpreter.getData().addSimulatorBindVal("/controls/flight/speedbrake",Double.parseDouble(values[21]));
				FlightSimulatorInterpreter.getData().addSimulatorBindVal("/controls/engines/current-engine/throttle", Double.parseDouble(values[22]));
				FlightSimulatorInterpreter.getData().addSimulatorBindVal("/engines/engine/rpm", Double.parseDouble(values[23]));
				FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/heading-indicator/offset-deg", Double.parseDouble(values[24]));
				
			}
		}
	}

	public FlightSimulatorClientHandler(int frequency) {
		this.frequency = frequency;

		// for test only
//		FlightSimulatorInterpreter.getData().addSimulatorBindVal("simX", 0.0);
//		FlightSimulatorInterpreter.getData().addSimulatorBindVal("simY", 0.0);
//		FlightSimulatorInterpreter.getData().addSimulatorBindVal("simZ", 0.0);
		
		FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/airspeed-indicator/indicated-speed-kt",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/altimeter/indicated-altitude-ft",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/altimeter/pressure-alt-ft",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/attitude-indicator/indicated-pitch-deg",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/attitude-indicator/indicated-roll-deg",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/attitude-indicator/internal-pitch-deg",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/attitude-indicator/internal-roll-deg",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/encoder/indicated-altitude-ft",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/encoder/pressure-alt-ft",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/gps/indicated-altitude-ft",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/gps/indicated-ground-speed-kt",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/gps/indicated-vertical-speed",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/heading-indicator/indicated-heading-deg",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/magnetic-compass/indicated-heading-deg",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/slip-skid-ball/indicated-slip-skid",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/turn-indicator/indicated-turn-rate",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/vertical-speed-indicator/indicated-speed-fpm",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindVal("/controls/flight/aileron", 0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindVal("/controls/flight/elevator", 0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindVal("/controls/flight/rudder",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindVal("/controls/flight/flaps",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindVal("/controls/flight/speedbrake",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindVal("/controls/engines/current-engine/throttle",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindVal("/engines/engine/rpm", 0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindVal("/instrumentation/heading-indicator/offset-deg",0.0);
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