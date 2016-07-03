package com.main;

public class Server { 
	
	/**
	 * main
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		int port = 8080;
		Connector con = new Connector();
		con.bind(port);
	}

}
