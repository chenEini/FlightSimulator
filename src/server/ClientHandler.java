package server;

import java.io.InputStream;
import java.io.OutputStream;

public interface ClientHandler {

	public void handleClient(InputStream input, OutputStream output);
}