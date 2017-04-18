package dab;

import dab.client.GameClient;

public class ClientExec implements Runnable {
	private String[] args;
	
	public String[] getArgs() {
		return args;
	}
	
	public void setArgs(String[] args) {
		this.args = args;
	}
	
	public ClientExec(String[] args) {
		this.args = args;
	}
	
	@Override
	public void run() {
		GameClient.main(args);
	}
}
