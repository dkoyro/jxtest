package by.dk.jx.test.rest;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Path("/")
public class TestResource {
	@Context
	private HttpHeaders headers;

	@GET
	public String get() throws UnknownHostException {
		InetAddress address = InetAddress.getLocalHost();
		StringBuilder result = new StringBuilder("Hello World!<br>");
		result.append("Host: ").append(address.getHostName()).append("<br>");
		headers.getRequestHeaders().forEach((k, v) -> result.append(k).append(" = ").append(v).append("<br>"));
		return result.toString();
	}
}
