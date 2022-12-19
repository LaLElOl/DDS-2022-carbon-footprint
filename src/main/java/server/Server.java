package server;

import spark.servlet.SparkApplication;

public class Server implements SparkApplication {

	public static void main(String[] args) {
		//Spark.port(8081);
		new Server().init();
		//DebugScreen.enableDebugScreen();
	}

	@Override
	public void init() {
		Router.init();
	}
}

