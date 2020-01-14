package server;

import java.io.*;
import java.net.*;

public class MySerialServer implements Server {
	private int port;
	private ClientHandler handler;
	private volatile boolean stop;

	public MySerialServer(int port, ClientHandler handler) {
		this.port = port;
		this.handler = handler;
		stop = false;
	}

	public void start() {
		new Thread(() -> {
			try {
				runServer();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	@Override
	public void open(int port, ClientHandler c) {
	}

	@Override
	public void stop() {
		stop = true;
	}

	// private methods
	private void runServer() throws Exception {
		ServerSocket server = new ServerSocket(port);
		server.setSoTimeout(1000);
		while (!stop) {
			try {
				Socket client = server.accept();
				try {
					handler.handleClient(client.getInputStream(), client.getOutputStream());
					client.getInputStream().close();
					client.getOutputStream().close();
					client.close();
				} catch (IOException e) {
				}
			} catch (SocketTimeoutException e) {
			}
		}
		server.close();
	}
}