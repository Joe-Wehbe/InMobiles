/*
 This Java code defines a class named App that sets up and starts a Grizzly HTTP 
 server with WebSocket support, which can serve RESTful web services and handle 
 WebSocket communication. Grizzly is a framework that provides a high-performance 
 and scalable network application framework for building servers and clients.
*/
package net.lakis.webapi;

/*
  The code imports various classes from different packages, including Grizzly, 
  Jersey (for JAX-RS), and custom classes. These classes are required for 
  configuring and running the server, handling HTTP and WebSocket communication, 
  and registering JAX-RS resources.
*/
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import org.glassfish.grizzly.PortRange;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.http.server.ServerConfiguration;
import org.glassfish.grizzly.nio.transport.TCPNIOTransport;
import org.glassfish.grizzly.strategies.WorkerThreadIOStrategy;
import org.glassfish.grizzly.threadpool.ThreadPoolConfig;
import org.glassfish.grizzly.websockets.WebSocketAddOn;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpContainer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpContainerProvider;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class App {
	
	private HttpServer server;
	ConnectionDB connection = new ConnectionDB();
	
/*
	The main method is the entry point of the application. It initializes an 
	instance of the App class, calls its main method, and then enters an infinite 
	sleep. This effectively keeps the application running indefinitely.
*/
	public static void main(String[] args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException,
			NoSuchMethodException, SecurityException, IOException, InterruptedException, ClassNotFoundException, SQLException {
		System.out.println(System.getProperty("user.dir")); // prints the current working directory

		new App().main();
		Thread.sleep(Long.MAX_VALUE); // Makes the server run for a very long time
	}
	
	
	// This method is responsible for setting up and starting the Grizzly HTTP server
	private void main() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			InstantiationException, NoSuchMethodException, SecurityException, IOException, ClassNotFoundException, SQLException {
		
		// Creates a new instance of the HttpServer class. This will be the Grizzly HTTP server that handles incoming requests.
		this.server = new HttpServer();

		// Retrieves the server's configuration object. The serverConfiguration object provides access to various configuration settings for the server.
		ServerConfiguration serverConfiguration = this.server.getServerConfiguration();
		
		// Configures the server to allow payloads (request bodies) for undefined HTTP methods. This can be useful if your application uses non-standard HTTP methods.
		serverConfiguration.setAllowPayloadForUndefinedHttpMethods(true);
		
		// Calls the addNetworkListeners method to configure network listeners, thread pools, and WebSocket support for the server.
		this.addNetworkListeners();

		// To configure and add JAX-RS servlets to the server. This method likely sets up the routing and handling of RESTful requests.
		this.addServletsFromPackageName(server, serverConfiguration);

		// Enables JMX (Java Management Extensions) monitoring for the server. JMX allows for management and monitoring of applications in a standard way.
		serverConfiguration.setJmxEnabled(true);

//		WebSocketEngine.getEngine().register("", "/Echo", new Echo());

		connection.connect();
		this.server.start();
	}
	
	// This method configures JAX-RS servlets for the server to handle RESTful requests:
	private void addServletsFromPackageName(HttpServer server, ServerConfiguration serverConfiguration)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException,
			NoSuchMethodException, SecurityException, IOException { 

		/*
		 - This creates a new instance of ResourceConfig, which is a configuration object 
		 used by JAX-RS to manage resources and features.
		 - packages("net.lakis") specifies the package(s) where JAX-RS should look for 
		 resource classes. In this case, it's set to package net.lakis.
		 - .register(JacksonFeature.class) registers the JacksonFeature class. 
		 This enables JSON processing and serialization/deserialization using the Jackson 
		 library for JAX-RS resources. */
		final ResourceConfig rc = new ResourceConfig().packages("net.lakis").register(JacksonFeature.class)//
				.register(JsonProvider.class);// .reg();
		
		/*
		 - Creates an instance of GrizzlyHttpContainerProvider, which is used to create a 
		 GrizzlyHttpContainer instance.
         - .createContainer(GrizzlyHttpContainer.class, rc) creates a new GrizzlyHttpContainer 
         using the previously configured ResourceConfig (rc).
		*/
		GrizzlyHttpContainer httpContainer = new GrizzlyHttpContainerProvider()
				.createContainer(GrizzlyHttpContainer.class, rc);
		
		/*
		 - Adds the httpContainer as an HTTP handler to the server's configuration.
		 - The handler will handle requests at the root context path ("/"). 
		 This means that any incoming requests to the root URL will be routed to the 
		 GrizzlyHttpContainer for further processing by JAX-RS.
		*/
		serverConfiguration.addHttpHandler(httpContainer, "/");
	}
	
	// This method configures network listeners, thread pools, and WebSocket support:
	private void addNetworkListeners() {

		// Creates an instance of WebSocketAddOn, which is an add-on used to enable WebSocket support for the server.
		WebSocketAddOn webSocketAddon = new WebSocketAddOn();

		// Creates a NetworkListener named "myServer" that listens on IP address "0.0.0.0" and port 1559.
		// The PortRange(1559, 1559) specifies that only port 1559 will be used for incoming connections.
		NetworkListener listener = new NetworkListener("myServer", "0.0.0.0", new PortRange(1559, 1559));
		
		// Registers the webSocketAddon with the network listener.
		// This enables WebSocket support for the listener, allowing the server to handle WebSocket connections.
		listener.registerAddOn(webSocketAddon);

		// Adds the configured network listener to the server.
		// The server will now use this network listener to listen for incoming connections.
		this.server.addListener(listener);

		// Retrieves the TCPNIOTransport associated with the network listener.
		// The TCPNIOTransport provides access to various transport-level settings for the listener.
		TCPNIOTransport transport = listener.getTransport();

		// Configures a thread pool named "worker-thread-" with 10 core threads, a maximum of 100 threads, and no queue limit (indicated by -1).
		// The thread pool will be used to handle incoming connections and process WebSocket communication.
		ThreadPoolConfig conf = ThreadPoolConfig.defaultConfig().setPoolName("worker-thread-").setCorePoolSize(10)
				.setMaxPoolSize(100).setQueueLimit(-1)/* same as default */;

		// Sets the transport to non-blocking mode. This allows the server to handle multiple connections concurrently using a single thread.
		transport.configureBlocking(false);
		// Sets the number of selector runners to 4. Selector runners handle I/O operations for the server.
		transport.setSelectorRunnersCount(4);
		// Configures the transport to use the thread pool defined by the ThreadPoolConfig.
		transport.setWorkerThreadPoolConfig(conf);
		// Sets the I/O strategy to use WorkerThreadIOStrategy, which is a strategy for handling I/O using worker threads.
		transport.setIOStrategy(WorkerThreadIOStrategy.getInstance());
		// Enables TCP no-delay mode, which reduces the latency for small packets.
		transport.setTcpNoDelay(true);
		// Sets the read buffer size to 1,000,000 bytes (1 MB). This affects the amount of data that can be read at once from the network.
		transport.setReadBufferSize(1000000);
		// Enables address reuse, which allows the server to bind to an address that is already in use.
		transport.setReuseAddress(true);

	}
}
