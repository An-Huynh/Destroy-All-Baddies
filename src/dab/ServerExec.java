package dab;

import dab.server.GameServer;

public class ServerExec implements Runnable {
	@Override
	public void run() {
		GameServer.main(null);
	}
}
