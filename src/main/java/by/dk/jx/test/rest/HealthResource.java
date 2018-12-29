package by.dk.jx.test.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("actuator/health")
public class HealthResource {

	@GET
	public Response get() {
		return Response.ok().build();
	}
}
