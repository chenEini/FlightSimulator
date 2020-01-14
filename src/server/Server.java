package server;

public interface Server {

	public void open(int port, ClientHandler handler);

	public void stop();
}