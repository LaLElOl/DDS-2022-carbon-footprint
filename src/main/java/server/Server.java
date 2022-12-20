package server;

import spark.servlet.SparkApplication;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

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

	@WebFilter(
			filterName = "SparkInitFilter",
			urlPatterns = {"/*"},
			initParams = {@WebInitParam(name = "applicationClass", value = "Server")})
	public static class SparkInitFilter extends spark.servlet.SparkFilter {}
}

